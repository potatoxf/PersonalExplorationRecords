/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.reflect;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents the key to looking up a Method by reflection.
 *
 * @author potatoxf
 */
public final class MethodDescriptor extends MemberDescriptor<MethodDescriptor> {

    private final Type returnType;
    private final Class<?>[] parameterTypes;

    public MethodDescriptor(String name, Class<?> location, Integer modifiers, Type returnType, Class<?>[] parameterTypes) {
        super(name, location, modifiers);
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
    }

    public Type returnType() {
        return returnType;
    }

    public Class<?>[] parameterTypes() {
        return parameterTypes;
    }

    @Override
    protected int compareToWithOther(MethodDescriptor other) {
        int c = Integer.compare(parameterTypes.length, other.parameterTypes.length);
        if (c != 0) return c;
        c = super.compareToWithOther(other);
        if (c != 0) return c;
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] != other.parameterTypes[i]) {
                return Boolean.compare(parameterTypes[i].isPrimitive(), other.parameterTypes[i].isPrimitive());
            }
        }
        Type t = returnType(), ot = other.returnType();
        if (t != null && ot != null) {
            return t.getTypeName().compareTo(ot.getTypeName());
        } else {
            if (t != null) return 1;
            if (ot != null) return -1;
        }
        return c;
    }

    @Override
    protected boolean equalsWithOther(MemberDescriptor<?> other) {
        if (other instanceof LookupDescriptor) {
            LookupDescriptor lookupDescriptor = (LookupDescriptor) other;
            Class<?> fieldType = lookupDescriptor.fieldType();
            Class<?>[] parameterTypes = parameterTypes();
            if (fieldType != null) {
                if (parameterTypes == null || parameterTypes.length != 1) return false;
                return fieldType == parameterTypes[0];
            } else {
                return Arrays.equals(parameterTypes, lookupDescriptor.parameterTypes());
            }
        } else {
            MethodDescriptor o = (MethodDescriptor) other;
            boolean equals = Arrays.equals(parameterTypes(), o.parameterTypes());
            if (equals) {
                equals = Objects.equals(returnType(), o.returnType());
            }
            return equals;
        }
    }
}