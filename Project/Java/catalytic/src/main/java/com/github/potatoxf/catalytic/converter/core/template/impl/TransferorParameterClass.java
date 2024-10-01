/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core.template.impl;

import com.github.potatoxf.catalytic.converter.core.template.ParameterClass;

/**
 * @author potatoxf
 */
public class TransferorParameterClass extends ParameterClass<TransferorParameterClass> {

    public TransferorParameterClass(Class<?> from, Class<?> to) {
        super(from, to);
    }

    public Class<?> origin() {
        return at(0);
    }

    public Class<?> target() {
        return at(1);
    }
}
