/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core;

import java.util.Objects;

/**
 * 转换的键
 *
 * @author potatoxf
 */
public class ConverterKey implements Comparable<ConverterKey> {
    private final Class<?> originClass;
    private final Class<?> targetClass;

    public ConverterKey(Class<?> originClass, Class<?> targetClass) {
        this.originClass = originClass;
        this.targetClass = targetClass;
    }

    public Class<?> getOriginClass() {
        return originClass;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    @Override
    public int compareTo(ConverterKey o) {
        int i = targetClass.getSimpleName().compareTo(o.targetClass.getSimpleName());
        if (i == 0) {
            return originClass.getSimpleName().compareTo(o.originClass.getSimpleName());
        }
        return i;
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ConverterKey)) return false;

        ConverterKey converterKey = (ConverterKey) object;
        return Objects.equals(originClass, converterKey.originClass)
                && Objects.equals(targetClass, converterKey.targetClass);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(originClass);
        result = 31 * result + Objects.hashCode(targetClass);
        return result;
    }
}
