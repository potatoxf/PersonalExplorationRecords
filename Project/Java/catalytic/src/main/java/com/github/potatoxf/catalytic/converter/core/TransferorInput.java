/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core;

/**
 * @author potatoxf
 */
public class TransferorInput extends AbstractConverterInput {

    protected TransferorInput() {
    }

    public static TransferorInput of() {
        return new TransferorInput();
    }

    @Override
    protected TransferorInput create() {
        return TransferorInput.of();
    }

    @Override
    public TransferorInput copy() {
        return (TransferorInput) super.copy();
    }

    @Override
    public TransferorInput useOrigin(Object origin) {
        super.useOrigin(origin);
        return this;
    }

    @Override
    public TransferorInput useTarget(Object target) {
        super.useTarget(target);
        return this;
    }

    @Override
    public TransferorInput useTarget(Class<?> targetType) {
        super.useTarget(targetType);
        return this;
    }
}
