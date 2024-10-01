/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core;

import com.github.potatoxf.catalytic.CatalyticException;

/**
 * 传输器
 *
 * @author potatoxf
 */
@SuppressWarnings("unchecked")
public abstract class Transferor<Origin, Target> extends AbstractConverter<Origin, Target, TransferorInput> {
    protected Transferor() {
    }

    @Override
    protected final boolean isTargetMustBeSet() {
        return true;
    }

    @Override
    public final Target apply(TransferorInput input) throws Throwable {
        return this.apply((Origin) input.origin(), (Target) input.target());
    }

    /**
     * @param origin
     * @param target
     * @return
     * @throws CatalyticException
     */
    public abstract Target apply(Origin origin, Target target) throws CatalyticException;
}
