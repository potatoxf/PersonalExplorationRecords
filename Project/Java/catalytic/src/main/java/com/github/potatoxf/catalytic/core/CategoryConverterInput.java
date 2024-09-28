/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core;

/**
 * 类别转换器的输入参数转换参数
 *
 * @author potatoxf
 */
public class CategoryConverterInput extends BasicConverterInput {

    protected CategoryConverterInput() {
    }

    public static CategoryConverterInput of() {
        return new CategoryConverterInput();
    }

    @Override
    public CategoryConverterInput useOrigin(Object origin) {
        super.useOrigin(origin);
        return this;
    }

    @Override
    public CategoryConverterInput useTarget(Object target) {
        super.useTarget(target);
        return this;
    }

    @Override
    public CategoryConverterInput useTarget(Class<?> targetType) {
        super.useTarget(targetType);
        return this;
    }
}
