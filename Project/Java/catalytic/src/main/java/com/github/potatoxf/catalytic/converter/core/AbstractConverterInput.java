/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core;

import com.github.potatoxf.catalytic.utils.reflect.Reflects;

/**
 * 抽象转换器的输入参数转换参数
 *
 * @author potatoxf
 */
public abstract class AbstractConverterInput {
    private Object origin;
    private Object target;
    private boolean targetType;

    protected AbstractConverterInput() {
    }

    protected abstract AbstractConverterInput create();

    public AbstractConverterInput copy() {
        AbstractConverterInput other = create();
        if (this != other) {
            other.origin = origin;
            other.target = target;
            other.targetType = targetType;
        }
        return this;
    }

    public Object origin() {
        return origin;
    }

    public Object target() {
        if (targetType && target instanceof Class) {
            try {
                target = Reflects.newInstance((Class<?>) target);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return target;
    }

    public AbstractConverterInput useOrigin(Object origin) {
        this.origin = origin;
        return this;
    }

    public AbstractConverterInput useTarget(Object target) {
        this.target = target;
        this.targetType = false;
        return this;
    }

    public AbstractConverterInput useTarget(Class<?> targetType) {
        this.target = targetType;
        this.targetType = true;
        return this;
    }
}
