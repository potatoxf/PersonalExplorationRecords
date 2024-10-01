/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter;

/**
 * 转换结果
 *
 * @author potatoxf
 */
public class ConversionResult<R> {

    private final R result;
    private final boolean isDefault;
    private final Throwable throwable;

    public ConversionResult(R result, boolean isDefault, Throwable throwable) {
        this.result = result;
        this.isDefault = isDefault;
        this.throwable = throwable;
    }

    public R getResult() {
        return result;
    }

    public boolean isDefault() {
        return isDefault;
    }

    /**
     * 判断是否有异常
     */
    public boolean hasException() {
        return throwable != null;
    }
}
