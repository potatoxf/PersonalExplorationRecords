/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core.template;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author potatoxf
 */
public abstract class ParameterClass<C extends ParameterClass<C>> implements Iterable<Class<?>> {
    private final Class<?>[] parameters;

    protected ParameterClass(Class<?>... parameters) {
        this.parameters = parameters;
    }

    /**
     * @param index
     * @return
     */
    protected final Class<?> at(int index) {
        return parameters[index];
    }

    @Override
    public Iterator<Class<?>> iterator() {
        return Arrays.asList(parameters).iterator();
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ParameterClass)) return false;
        return Arrays.equals(parameters, ((ParameterClass<?>) object).parameters);
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
