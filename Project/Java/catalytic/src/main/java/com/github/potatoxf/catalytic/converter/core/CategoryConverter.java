/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core;

/**
 * 类别转换器
 *
 * @author potatoxf
 */
public abstract class CategoryConverter<Target, I extends CategoryConverterInput> extends BasicConverter<Object, Target, I> {

    /**
     * 加载来源类型
     *
     * @return {@code Class<Origin> }
     */
    @Override
    protected abstract Class<Object> loadOriginType();
}
