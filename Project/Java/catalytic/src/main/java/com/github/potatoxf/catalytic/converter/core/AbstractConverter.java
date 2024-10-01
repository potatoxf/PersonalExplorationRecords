/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core;

import com.github.potatoxf.catalytic.converter.ConversionResult;
import com.github.potatoxf.catalytic.utils.reflect.Reflects;

/**
 * 抽象转换器，应用所有转换器类型
 *
 * @param <Origin> 来源类型
 * @param <Target> 目标类型
 * @param <I>      输入参数转换参数
 * @author potatoxf
 */
@SuppressWarnings("unchecked")
public abstract class AbstractConverter<Origin, Target, I extends AbstractConverterInput> {
    /**
     * 来源类型
     */
    private volatile Class<Origin> originType;
    /**
     * 目标类型
     */
    private volatile Class<Target> targetType;
    /**
     * 来源类型是否支持
     */
    private volatile Boolean originSupport;
    /**
     * 目标类型是否支持
     */
    private volatile Boolean targetSupport;

    /**
     * 来源类型
     *
     * @return {@code Class<Origin>}
     */
    public final Class<Origin> originType() {
        if (originType == null) {
            originType = loadOriginType();
            originSupport = originType != null;
        }
        return originType;
    }

    /**
     * 加载来源类型
     *
     * @return {@code Class<Origin>}
     */
    protected Class<Origin> loadOriginType() {
        return (Class<Origin>) Reflects.lookupSuperclassGenericType(getClass(), AbstractConverter.class, 0);
    }

    /**
     * 目标类型
     *
     * @return {@code Class<Target>}
     */
    public final Class<Target> targetType() {
        if (targetType == null) {
            targetType = loadTargetType();
            targetSupport = targetType != null;
        }
        return targetType;
    }

    /**
     * 加载目标类型
     *
     * @return {@code Class<Target>}
     */
    protected Class<Target> loadTargetType() {
        return (Class<Target>) Reflects.lookupSuperclassGenericType(getClass(), AbstractConverter.class, 1);
    }

    /**
     * 是否正确加载转换器，如果不是正确转换器则不支持使用
     *
     * @return 如果是正确转换器则返回true，否则返回false
     */
    public boolean isLoadedCorrectlyConverter() {
        return ((originSupport != null && originSupport) || (originSupport == null && originType() != null))
                && ((targetSupport != null && targetSupport) || (targetSupport == null && targetType() != null));
    }

    /**
     * @param origin
     * @param target
     * @return
     */
    public boolean isSupport(Object origin, Object target) {
        if (origin == null || target == null) return false;
        Class<Origin> originClass = originType();
        Class<Target> targetClass = targetType();
        return originClass.isInstance(origin) && targetClass.isInstance(targetClass);
    }

    /**
     * 应用转换并检查
     *
     * @param defaultValue 默认值
     * @return {@code ConversionResult<Target>}返回转换结果
     */
    public final ConversionResult<Target> applyAndCheck(I input, Target defaultValue) {
        if (!this.isLoadedCorrectlyConverter()) {
            throw new UnsupportedOperationException("该转换器不支持环境操作");
        }
        Object origin = input.origin();
        Object target = input.target();
        if (origin == null) {
            throw new IllegalArgumentException("The input Origin Object is null");
        }
        if (!originType().isInstance(origin)) {
            throw new IllegalArgumentException("The input Origin Object is not of type " + originType());
        }
        if (isTargetMustBeSet()) {
            if (target == null) {
                throw new IllegalArgumentException("The input Target Object is null");
            } else if (!targetType().isInstance(target)) {
                throw new IllegalArgumentException("The input Target Object is not of type " + targetType());
            }
        }
        Target newTarget = null;
        try {
            Object temp = this.apply(input);
            if (temp != null) {
                newTarget = targetType().cast(temp);
            }
        } catch (Throwable e) {
            return new ConversionResult<>(defaultValue, true, e);
        }
        return new ConversionResult<>(newTarget != null ? newTarget : (defaultValue != null ? defaultValue : (Target) target), false, null);
    }

    /**
     * 应用转换
     *
     * @param input 输入参数转换参数
     * @return 返回转换后的值
     * @throws Throwable 抛出转换的任何异常
     */
    public abstract Target apply(I input) throws Throwable;

    /**
     * 是否目标必须被设置
     *
     * @return 如果目标必须被设置则为true，否则返回false
     */
    protected boolean isTargetMustBeSet() {
        return false;
    }
}
