/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author potatoxf
 */
public final class AttributeInfo {
    private final AttributeName attributeName;
    private final Field attributeField;
    private final Method getterMethod;
    private final Method setterMethod;

    public AttributeInfo(AttributeName attributeName, Field attributeField, Method getterMethod, Method setterMethod) {
        this.attributeName = attributeName;
        this.attributeField = attributeField;
        this.getterMethod = getterMethod;
        this.setterMethod = setterMethod;
    }

    public AttributeName getAttributeName() {
        return attributeName;
    }

    public Field getAttributeField() {
        return attributeField;
    }

    public Method getGetterMethod() {
        return getterMethod;
    }

    public Method getSetterMethod() {
        return setterMethod;
    }

    public String getGetterName() {
        return getterMethod.getName();
    }

    public String getSetterName() {
        return setterMethod.getName();
    }

    @Override
    public String toString() {
        return "AttributeInfo{" +
                "attributeName=" + attributeName +
                ", field=" + attributeField +
                ", getter=" + getterMethod +
                ", setter=" + setterMethod +
                '}';
    }
}
