/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core;

/**
 * 基本转换器
 *
 * @author potatoxf
 */
@SuppressWarnings("unchecked")
public abstract class BasicConverter<Origin, Target, I extends BasicConverterInput> extends AbstractConverter<Origin, Target, I> {

    /**
     * 转换到类型
     *
     * @param input 输入参数
     * @return 返回结果类型
     * @throws Throwable 抛出转换异常
     */
    @Override
    public final Target apply(I input) throws Throwable {
        return this.apply((Origin) input.origin(), input);
    }

    /**
     * 转换到类型
     *
     * @param value 输入值
     * @param input 输入参数
     * @return 返回结果类型
     * @throws Throwable 抛出转换异常
     */
    protected abstract Target apply(Origin value, I input) throws Throwable;
}
