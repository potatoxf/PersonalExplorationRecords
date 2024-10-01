/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils;

import java.util.function.Function;

/**
 * lambda函数式类型值
 *
 * @author potatoxf
 */
public class FunctionClassValue<T> extends ClassValue<T> {
    private final Function<Class<?>, T> function;

    public FunctionClassValue(Function<Class<?>, T> function) {
        this.function = function;
    }

    public static <T> ClassValue<T> withInitial(Function<Class<?>, T> function) {
        return new FunctionClassValue<>(function);
    }

    /**
     * Computes the given class's derived value for this {@code ClassValue}.
     * <p>
     * This method will be invoked within the first thread that accesses
     * the value with the {@link #get get} method.
     * <p>
     * Normally, this method is invoked at most once per class,
     * but it may be invoked again if there has been a call to
     * {@link #remove remove}.
     * <p>
     * If this method throws an exception, the corresponding call to {@code get}
     * will terminate abnormally with that exception, and no class value will be recorded.
     *
     * @param type the type whose class value must be computed
     * @return the newly computed value associated with this {@code ClassValue}, for the given class or interface
     * @see #get
     * @see #remove
     */
    @Override
    protected T computeValue(Class<?> type) {
        if (function != null) {
            return function.apply(type);
        }
        return null;
    }
}
