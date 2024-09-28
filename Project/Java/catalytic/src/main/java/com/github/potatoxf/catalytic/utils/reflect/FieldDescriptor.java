/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Represents the key to looking up a Method by reflection.
 *
 * @author potatoxf
 */
public final class FieldDescriptor extends MemberDescriptor<FieldDescriptor> {
    private final Type fieldType;

    public FieldDescriptor(String name, Class<?> location, Integer modifiers, Type fieldType) {
        super(name, location, modifiers);
        this.fieldType = fieldType;
    }

    public Type fieldType() {
        return fieldType;
    }

    @Override
    protected int compareToWithOther(FieldDescriptor other) {
        Type t = fieldType(), ot = other.fieldType();
        if (t != null && ot != null) {
            return t.getTypeName().compareTo(ot.getTypeName());
        } else {
            if (t != null) return 1;
            if (ot != null) return -1;
        }
        return 0;
    }

    @Override
    protected boolean equalsWithOther(MemberDescriptor<?> memberDescriptor) {
        if (memberDescriptor instanceof LookupDescriptor) {
            LookupDescriptor lookupDescriptor = (LookupDescriptor) memberDescriptor;
            Class<?> fieldType = lookupDescriptor.fieldType();
            Type genericFieldType = fieldType();
            if (fieldType != null && genericFieldType != null) {
                if (genericFieldType instanceof Class) {
                    return genericFieldType == fieldType;
                } else if (genericFieldType instanceof ParameterizedType) {
                    return ((ParameterizedType) genericFieldType).getRawType() == fieldType;
                } else {
                    return false;
                }
            }
            return false;
        } else {
            FieldDescriptor fieldDescriptor = (FieldDescriptor) memberDescriptor;
            return Objects.equals(fieldType(), fieldDescriptor.fieldType());
        }
    }
}