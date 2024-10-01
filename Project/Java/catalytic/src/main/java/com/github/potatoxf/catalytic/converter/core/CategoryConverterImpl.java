/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core;

/**
 * 类别转换器
 *
 * @author potatoxf
 */
public abstract class CategoryConverterImpl<Target> extends CategoryConverter<Target, CategoryConverterInput> {

    private final Class<?> originType;

    protected CategoryConverterImpl(Class<?> originType) {
        this.originType = originType;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<Object> loadOriginType() {
        return (Class<Object>) originType;
    }
}
