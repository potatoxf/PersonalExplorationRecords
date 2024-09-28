/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core.template.impl;

import com.github.potatoxf.catalytic.core.template.ParameterObject;

/**
 * @author potatoxf
 */
public class TransferorParameterObject extends ParameterObject<TransferorParameterClass, TransferorParameterObject> {

    public TransferorParameterObject(Object from, Object to) {
        super(from, to);
    }

    @Override
    public TransferorParameterClass toParameterClass() {
        return new TransferorParameterClass(at(0).getClass(), at(1).getClass());
    }

    public Object origin() {
        return at(0);
    }

    public Object target() {
        return at(1);
    }
}
