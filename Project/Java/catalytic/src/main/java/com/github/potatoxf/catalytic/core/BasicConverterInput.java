/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core;

/**
 * 基本转换器的输入参数转换参数
 *
 * @author potatoxf
 */
public class BasicConverterInput extends AbstractConverterInput {

    protected BasicConverterInput() {
    }

    public static BasicConverterInput of() {
        return new BasicConverterInput();
    }

    @Override
    protected BasicConverterInput create() {
        return BasicConverterInput.of();
    }

    @Override
    public BasicConverterInput copy() {
        return (BasicConverterInput) super.copy();
    }

    @Override
    public BasicConverterInput useOrigin(Object origin) {
        super.useOrigin(origin);
        return this;
    }

    @Override
    public BasicConverterInput useTarget(Object target) {
        super.useTarget(target);
        return this;
    }

    @Override
    public BasicConverterInput useTarget(Class<?> targetType) {
        super.useTarget(targetType);
        return this;
    }
}
