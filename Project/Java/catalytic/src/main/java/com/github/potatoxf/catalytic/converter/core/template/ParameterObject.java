/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core.template;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author potatoxf
 */
public abstract class ParameterObject<C extends ParameterClass<C>, O extends ParameterObject<C, O>> implements Iterable<Object> {
    private final Object[] parameters;

    protected ParameterObject(Object... parameters) {
        this.parameters = parameters;
    }

    /**
     * @param index
     * @return
     */
    protected final Object at(int index) {
        return parameters[index];
    }

    /**
     * @return
     */
    public abstract C toParameterClass();

    @Override
    public Iterator<Object> iterator() {
        return Arrays.asList(parameters).iterator();
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ParameterObject)) return false;
        return Arrays.equals(parameters, ((ParameterObject<?, ?>) object).parameters);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(parameters);
    }

    @Override
    public String toString() {
        return Arrays.toString(parameters);
    }
}
