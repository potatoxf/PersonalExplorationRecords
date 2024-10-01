/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core.template;

import com.github.potatoxf.catalytic.utils.PathClassLoader;
import com.github.potatoxf.catalytic.CatalyticException;
import com.github.potatoxf.catalytic.utils.reflect.Reflects;

import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个模板对应一个抽象类，实现接口
 *
 * @author potatoxf
 */
public abstract class TemplateGenerator<T, C extends ParameterClass<C>, O extends ParameterObject<C, O>> extends AbstractGenerator<C, O> {
    private static final String PLACEHOLDERS = "null";
    private final PathClassLoader pathClassLoader;
    private final ConcurrentHashMap<C, Cache> cache = new ConcurrentHashMap<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final AtomicReference<Class<T>> templateAbstractSuperClassRef = new AtomicReference<>();
    private final AtomicReference<AbstractGenerator<C, O>[]> classNameGeneratorsRef = new AtomicReference<>();
    private final AtomicReference<AbstractGenerator<C, O>[]> classBytecodeGeneratorsRef = new AtomicReference<>();

    protected TemplateGenerator(PathClassLoader pathClassLoader) {
        this.pathClassLoader = pathClassLoader;
    }

    /**
     * 注册类名生成器
     *
     * @param classNameGenerator 类名生成器
     */
    public final void registerClassNameGenerator(ClassNameGenerator<C, O> classNameGenerator) {
        Objects.requireNonNull(classNameGenerator, "The class name generator is null");
        this.add(classNameGeneratorsRef, classNameGenerator);
    }

    /**
     * 注册类字节码生成器
     *
     * @param classBytecodeGenerator 类字节码生成器
     */
    public final void registerClassBytecodeGenerator(ClassBytecodeGenerator<C, O> classBytecodeGenerator) {
        Objects.requireNonNull(classBytecodeGenerator, "The class bytecode generator is null");
        this.add(classBytecodeGeneratorsRef, classBytecodeGenerator);
    }

    /**
     * 模板抽象超类
     *
     * @return {@code Class<?>}
     */
    public final Class<T> getTemplateAbstractSuperClass() {
        Class<T> clz = templateAbstractSuperClassRef.get();
        if (clz == null) {
            clz = (Class<T>) Reflects.lookupSuperclassGenericType(getClass(), TemplateGenerator.class, 0);
        }
        if (clz == null) {
            throw new IllegalArgumentException("未提供模板抽象超类");
        }
        if (clz.isInterface()) {
            throw new IllegalArgumentException("提供模板抽象超类必须抽象类，而不是接口");
        }
        int modifiers = clz.getModifiers();
        if (!Modifier.isAbstract(modifiers)) {
            throw new IllegalArgumentException("提供模板抽象超类必须抽象类");
        }
        if (templateAbstractSuperClassRef.compareAndSet(null, clz)) {
            return clz;
        }
        return templateAbstractSuperClassRef.get();
    }

    /**
     * 加载模板类
     *
     * @param parameterClass  参数类
     * @param parameterObject 参数对象
     * @return 返回加载的模板类{@code Class<? extends T>}
     * @throws CatalyticException 如果发生异常
     */
    protected final Class<? extends T> loadTemplateType(C parameterClass, O parameterObject) throws CatalyticException {
        if (!this.isSupport(parameterClass, parameterObject)) {
            throw new CatalyticException("The input parameter is not supported by the TemplateGenerator");
        }
        Cache c = cache.computeIfAbsent(parameterClass, k -> new Cache());
        //1. 获取缓存的ClassName
        String name = c.className;
        // 存在，判断是否是占位符
        if (Objects.equals(name, PLACEHOLDERS)) {
            c.condition = lock.newCondition();
            while (Objects.equals((name = c.className), PLACEHOLDERS)) {
                try {
                    c.condition.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace(System.out);
                }
            }
        }
        // 不存在，先占位
        else {
            c.className = PLACEHOLDERS;
        }
        Class<?> result;
        //2. 转换器已经加载
        if (name != null) {
            try {
                result = pathClassLoader.loadClass(name);
            } catch (Throwable e) {
                throw new CatalyticException("Unable to load class of [" + name + "]", e);
            }
        }
        //3. 转换器未加载，则加载类生成
        else {
            //a. 生成类名
            ClassNameGenerator<C, O> foundClassNameGenerator = (ClassNameGenerator<C, O>) this.find(classNameGeneratorsRef, parameterClass, parameterObject);
            if (foundClassNameGenerator == null) {
                throw new CatalyticException("Failed to find a matching class name generator");
            }
            try {
                name = foundClassNameGenerator.generate(parameterClass, parameterObject);
            } catch (Throwable e) {
                throw new CatalyticException("Failed to generate class name", e);
            }
            //b. 查找生成器
            ClassBytecodeGenerator<C, O> foundClassBytecodeGenerator =
                    (ClassBytecodeGenerator<C, O>) this.find(classBytecodeGeneratorsRef, parameterClass, parameterObject);
            if (foundClassBytecodeGenerator == null) {
                throw new CatalyticException("Failed to find a matching class bytecode generator");
            }
            //c. 使用字节码生成器
            try {
                byte[] bytecode = foundClassBytecodeGenerator.generate(name, parameterClass, parameterObject);
                result = pathClassLoader.writeAndDefineClass(name, bytecode);
            } catch (Throwable e) {
                throw new CatalyticException("Failed to generate class bytecode", e);
            }
        }
        //4. 检查加载类
        if (result == null) {
            throw new CatalyticException("Failed to load class of [" + name + "]");
        }
        Class<T> templateAbstractSuperClass = getTemplateAbstractSuperClass();
        if (!templateAbstractSuperClass.isAssignableFrom(result)) {
            throw new CatalyticException("The class [" + result + "] does not extends abstract " + templateAbstractSuperClass.getName());
        }
        //5. 保存加载类型，并通知恢复类
        c.className = name;
        if (c.condition != null) {
            c.condition.signalAll();
            c.condition = null;
        }
        return (Class<? extends T>) result;
    }

    /**
     * 加载模板实例
     *
     * @param parameterClass  参数类
     * @param parameterObject 参数对象
     * @return 返回模板实例
     * @throws CatalyticException 如果发生异常
     */
    protected final T loadTemplateInstance(C parameterClass, O parameterObject) throws CatalyticException {
        Cache c = cache.computeIfAbsent(parameterClass, k -> new Cache());
        if (c.target != null) return c.target;
        Class<? extends T> templateType = this.loadTemplateType(parameterClass, parameterObject);
        T instance;
        try {
            instance = templateType.newInstance();
        } catch (Throwable e) {
            throw new CatalyticException("Error to new instance of [" + templateType + "]", e);
        }
        if (c.target == null) {
            c.target = instance;
        }
        return instance;
    }

    /**
     * 调用模板实例
     *
     * @param parameterClass  参数类
     * @param parameterObject 参数对象
     * @return 返回调用模板方法的返回值
     * @throws CatalyticException 如果发生异常
     */
    protected final Object invokeTemplateInstance(C parameterClass, O parameterObject) throws CatalyticException {
        T instance = this.loadTemplateInstance(parameterClass, parameterObject);
        return this.invokeTemplateInstance(instance, parameterClass, parameterObject);
    }

    /**
     * 调用模板实例
     *
     * @param instance        模板实例
     * @param parameterClass  参数类
     * @param parameterObject 参数对象
     * @return 返回调用模板方法的返回值
     * @throws CatalyticException 如果发生异常
     */
    protected abstract Object invokeTemplateInstance(T instance, C parameterClass, O parameterObject) throws CatalyticException;

    /**
     * 移除模板
     *
     * @param parameterClass 参数类
     */
    protected final void removeTemplate(C parameterClass) {
        if (parameterClass != null) {
            cache.remove(parameterClass);
        }
    }

    /**
     * 添加生成器
     *
     * @param ref     引用
     * @param element 生成器
     */
    private void add(AtomicReference<AbstractGenerator<C, O>[]> ref, AbstractGenerator<C, O> element) {
        AbstractGenerator<C, O>[] a = ref.get();
        if (a == null) {
            a = new AbstractGenerator[]{element};
        } else {
            for (AbstractGenerator<C, O> t : a) {
                if (t == element) return;
            }
            AbstractGenerator<C, O>[] b = new AbstractGenerator[a.length + 1];
            System.arraycopy(a, 0, b, 1, a.length);
            b[0] = element;
            a = b;
        }
        ref.set(a);
    }

    /**
     * 查找生成器
     *
     * @param ref             引用
     * @param parameterClass  参数类
     * @param parameterObject 参数对象
     * @return 返回生成器
     */
    private AbstractGenerator<C, O> find(AtomicReference<AbstractGenerator<C, O>[]> ref, C parameterClass, O parameterObject) {
        AbstractGenerator<C, O>[] ts = ref.get();
        if (ts != null) {
            for (AbstractGenerator<C, O> t : ts) {
                if (t.isSupport(parameterClass, parameterObject)) {
                    return t;
                }
            }
        }
        return null;
    }

    private class Cache {
        private volatile String className;
        private volatile Condition condition;
        private volatile T target;
    }
}
