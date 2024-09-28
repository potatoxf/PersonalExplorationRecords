/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author potatoxf
 */
public final class OncesThreadLocal<T> extends ThreadLocal<T> {
    private final Supplier<? extends T> supplier;
    private volatile T initialValue = null;
    private volatile boolean changed = false;

    OncesThreadLocal(Supplier<? extends T> supplier) {
        this.supplier = Objects.requireNonNull(supplier);
    }

    public static <S> OncesThreadLocal<S> withInitial(Supplier<? extends S> supplier) {
        return new OncesThreadLocal<>(supplier);
    }

    @Override
    protected T initialValue() {
        if (initialValue == null) {
            initialValue = supplier.get();
        }
        return initialValue;
    }

    /**
     * Sets the current thread's copy of this thread-local variable
     * to the specified value.  Most subclasses will have no need to
     * override this method, relying solely on the {@link #initialValue}
     * method to set the values of thread-locals.
     *
     * @param value the value to be stored in the current thread's copy of
     *              this thread-local.
     */
    @Override
    public void set(T value) {
        super.set(value);
        changed = true;
    }

    /**
     * Returns the value in the current thread's copy of this
     * thread-local variable.  If the variable has no value for the
     * current thread, it is first initialized to the value returned
     * by an invocation of the {@link #initialValue} method.
     *
     * @return the current thread's value of this thread-local
     */
    @Override
    public T get() {
        if (changed) {
            T old = super.get();
            super.set(initialValue);
            changed = false;
            return old;
        } else {
            return super.get();
        }
    }
}
