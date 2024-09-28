/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.reflect;

/**
 * @author potatoxf
 */
public final class LookupDescriptor extends MemberDescriptor<LookupDescriptor> {
    private final Boolean field;
    private final Class<?>[] types;

    public LookupDescriptor(String name) {
        this(name, (Boolean) null);
    }

    public LookupDescriptor(String name, Class<?> fieldType) {
        this(name, true, fieldType);
    }

    public LookupDescriptor(String name, Class<?>... types) {
        this(name, false, types);
    }

    private LookupDescriptor(String name, Boolean field, Class<?>... types) {
        super(name, null, null);
        this.field = field;
        this.types = types;
    }

    public Class<?> fieldType() {
        return field != null && field ? types[0] : null;
    }

    public Class<?>[] parameterTypes() {
        return field != null && !field ? types : null;
    }

    @Override
    protected boolean equalsWithOther(MemberDescriptor<?> memberDescriptor) {
        if (memberDescriptor instanceof FieldDescriptor) {

        } else if (memberDescriptor instanceof MethodDescriptor) {

        } else {
            return false;
        }
        return true;
    }
}
