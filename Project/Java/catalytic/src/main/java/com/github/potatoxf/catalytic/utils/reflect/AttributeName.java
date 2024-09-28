/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 属性名
 *
 * @author potatoxf
 */
public final class AttributeName {
    private final String[] attributePartNames;
    private final int hash;

    private AttributeName(String[] attributePartNames) {
        this.attributePartNames = attributePartNames;
        this.hash = Arrays.hashCode(attributePartNames);
    }

    public static AttributeName ofMethod(Method input) {
        String name = input.getName();
        if (name.length() > 3 && (name.startsWith("set") || name.startsWith("get"))) {
            return AttributeName.of(name);
        }
        if (name.length() > 2 && name.startsWith("is")) {
            return AttributeName.of(name);
        }
        return null;
    }

    public static AttributeName ofField(Field input) {
        return AttributeName.of(input.getName());
    }

    public static AttributeName of(String input) {
        int start = input.startsWith("is") ? 2 :
                (input.startsWith("get") || input.startsWith("set") ? 3 : 0);
        List<String> parts = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int len = input.length();
        for (int i = start; i < len; i++) {
            char c = input.charAt(i);
            if (Character.isUpperCase(c) || c == '_') {
                parts.add(sb.toString());
                sb.setLength(0);
            }
            sb.append(c);
        }
        parts.add(sb.toString());
        sb.setLength(0);
        return new AttributeName(parts.stream().filter(s -> !s.isEmpty())
                .map(String::toLowerCase).map(String::intern).toArray(String[]::new));
    }

    public String toUpperKebabCase() {
        return this.toKebabCase(true);
    }

    public String toUpperUnderline() {
        return this.toUnderline(true);
    }

    public String toUpperCamelCase() {
        return this.toCamelCase(true);
    }

    public String toLowerKebabCase() {
        return this.toKebabCase(false);
    }

    public String toLowerUnderline() {
        return this.toUnderline(false);
    }

    public String toLowerCamelCase() {
        return this.toCamelCase(false);
    }

    private String toKebabCase(boolean upper) {
        String string = String.join("-", attributePartNames);
        if (upper) string = string.toUpperCase();
        return string;
    }

    private String toUnderline(boolean upper) {
        String string = String.join("_", attributePartNames);
        if (upper) string = string.toUpperCase();
        return string;
    }

    private String toCamelCase(boolean upper) {
        char c = attributePartNames[0].charAt(0);
        if (upper) c = Character.toUpperCase(c);
        if (attributePartNames.length == 1) {
            if (attributePartNames[0].length() == 1) {
                return String.valueOf(c);
            } else {
                return c + attributePartNames[0].substring(1);
            }
        } else {
            StringBuilder sb = new StringBuilder();
            if (attributePartNames[0].length() == 1) {
                sb.append(c);
            } else {
                sb.append(c).append(attributePartNames[0], 1, attributePartNames[0].length());
            }
            for (int i = 1; i < attributePartNames.length; i++) {
                c = attributePartNames[i].charAt(0);
                c = Character.toUpperCase(c);
                sb.append(c);
                if (attributePartNames[i].length() > 1) {
                    sb.append(attributePartNames[i], 1, attributePartNames[i].length());
                }
            }
            return sb.toString();
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof AttributeName)) return false;
        AttributeName that = (AttributeName) object;
        int length = this.attributePartNames.length;
        if (length != that.attributePartNames.length) return false;
        for (int i = 0; i < length; i++) {
            if (!attributePartNames[i].equals(that.attributePartNames[i])) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public String toString() {
        return Arrays.toString(attributePartNames);
    }
}
