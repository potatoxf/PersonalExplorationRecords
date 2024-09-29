/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.reflect;

import com.github.potatoxf.catalytic.utils.WordTokens;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 属性名
 *
 * @author potatoxf
 */
public final class AttributeName {
    private final WordTokens wordTokens;

    private AttributeName(WordTokens wordTokens) {
        this.wordTokens = wordTokens;
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
        return new AttributeName(WordTokens.ofJava(input));
    }

    public String toUpperKebabCase() {
        return wordTokens.toUpperKebabCase();
    }

    public String toUpperUnderLine() {
        return wordTokens.toUpperUnderLine();
    }

    public String toUpperCamelCase() {
        return wordTokens.toUpperCamelCase();
    }

    public String toLowerKebabCase() {
        return wordTokens.toLowerKebabCase();
    }

    public String toLowerUnderLine() {
        return wordTokens.toLowerUnderLine();
    }

    public String toLowerCamelCase() {
        return wordTokens.toLowerCamelCase();
    }

    private String toKebabCase(boolean upper) {
        return wordTokens.toLowerUnderLine();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeName that = (AttributeName) o;
        return Objects.equals(wordTokens, that.wordTokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordTokens);
    }

    @Override
    public String toString() {
        return wordTokens.toString();
    }
}
