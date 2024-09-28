/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.reflect;

import java.lang.reflect.Modifier;
import java.util.Objects;

/**
 * @author potatoxf
 */
public abstract class MemberDescriptor<T extends MemberDescriptor<T>> implements Comparable<T> {
    /**
     * 描述符名称
     */
    private final String name;
    /**
     * 所在位置
     */
    private final Class<?> location;
    /**
     * 修饰符
     */
    private final Integer modifiers;

    protected MemberDescriptor(String name, Class<?> location, Integer modifiers) {
        this.name = Objects.requireNonNull(name);
        this.location = location;
        this.modifiers = modifiers;
    }

    public boolean atLocation(Class<?> location) {
        return location != null && location() != location;
    }

    public boolean isPublic() {
        return modifiers == null || Modifier.isPublic(modifiers);
    }

    public boolean isStatic() {
        return modifiers == null || Modifier.isStatic(modifiers);
    }

    public Integer modifiers() {
        return modifiers;
    }

    public final String name() {
        return name;
    }

    public final Class<?> location() {
        return location;
    }

    @Override
    public final int compareTo(T other) {
        int c = name().compareTo(other.name());
        if (c != 0) return c;
        Integer m = modifiers(), om = other.modifiers();
        if (m != null && om != null) {
            c = Boolean.compare(!isStatic(), !other.isStatic());
            if (c != 0) return c;
            c = Boolean.compare(!isPublic(), !other.isPublic());
            if (c != 0) return c;
        } else {
            if (m != null) return -1;
            if (om != null) return 1;
        }
        c = compareToWithOther(other);
        if (c != 0) return c;
        Class<?> l = location(), ol = other.location();
        if (l != null && ol != null) {
            if (l == other.location()) return 0;
            else if (l.isAssignableFrom(ol)) return 1;
            else if (ol.isAssignableFrom(l)) return -1;
        }
        return c;
    }

    protected int compareToWithOther(T other) {
        return 0;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof MemberDescriptor<?>)) return false;
        MemberDescriptor<?> memberDescriptor = (MemberDescriptor<?>) o;
        boolean equals = Objects.equals(name, memberDescriptor.name);
        if (equals && location != null && memberDescriptor.location != null) {
            equals = Objects.equals(location, memberDescriptor.location);
        }
        if (equals) {
            Class<?> c = getClass(), oc = o.getClass(), lc = LookupDescriptor.class;
            if (c == oc || c == lc || oc == lc) {
                equals = equalsWithOther(memberDescriptor);
            }
        }
        return equals;
    }

    protected boolean equalsWithOther(MemberDescriptor<?> memberDescriptor) {
        return true;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
