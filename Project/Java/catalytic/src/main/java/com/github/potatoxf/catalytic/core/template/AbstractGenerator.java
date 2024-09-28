/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core.template;

/**
 * @author potatoxf
 */
public abstract class AbstractGenerator<C extends ParameterClass<C>, O extends ParameterObject<C, O>> {

    protected static boolean testInclude(Class<?> clz, Class<?>... classes) {
        for (Class<?> target : classes) {
            if (target.isAssignableFrom(clz)) return true;
        }
        return false;
    }

    protected static boolean testInclude(Class<?> clz, String... classNames) {
        for (String className : classNames) {
            Class<?> target;
            try {
                target = Class.forName(className);
            } catch (Throwable e) {
                continue;
            }
            if (target.isAssignableFrom(clz)) return true;
        }
        return false;
    }

    /**
     * 判断输入参数是否支持
     *
     * @param parameterClass  输入参数
     * @param parameterObject 输入参数
     * @return 如果支持返回true，否则返回false
     */
    public abstract boolean isSupport(C parameterClass, O parameterObject);
}
