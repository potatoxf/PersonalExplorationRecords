/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 字符串Token实例
 *
 * @author potatoxf
 */
public final class StringToken {

    private static final BitSet STRING_TOKEN = new BitSet(128);

    static {
        for (int i = 0; i < STRING_TOKEN.length(); i++) {
            char c = (char) i;
            if (Character.isLetterOrDigit(c) || c == '_' || c == '-') {
                STRING_TOKEN.set(i);
            }
        }
    }

    public static StringToken ofJava(String string) {
        return StringToken.of(string, new AtomicReference<>(FormatType.LowerCamelCase));
    }

    public static StringToken ofDatabase(String string) {
        return StringToken.of(string, new AtomicReference<>(FormatType.UpperUnderLine));
    }

    public static StringToken ofHTML(String string) {
        return StringToken.of(string, new AtomicReference<>(FormatType.LowerKebabCase));
    }

    public static StringToken of(String string, AtomicReference<FormatType> formatType) {
        int[] codepoints = string.codePoints().toArray();
        List<char[]> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codepoints.length; i++) {
            int c = codepoints[i];
            if (!STRING_TOKEN.get(c)) {
                if (c == '_' || c == '-') {
                    if (sb.length() != 0) {
                        list.add(sb.toString().toCharArray());
                        sb.setLength(0);
                    }
                } else if (Character.isUpperCase(c)) {
                    if (sb.length() != 0) {
                        list.add(sb.toString().toCharArray());
                        sb.setLength(0);
                    }
                    int l = 0;
                    while (i + l + 1 < codepoints.length && Character.isUpperCase(codepoints[i + l + 1])) l++;
                    if (l > 0) {
                        if (i + l < codepoints.length) l--;
                        for (int j = 0; j <= l; j++) sb.append((char) codepoints[i + j]);
                        i += l;
                    } else {
                        if (Character.isUpperCase(c)) sb.append(Character.toLowerCase((char) c));
                    }
                } else {
                    sb.append((char) c);
                }
            } else {
                throw new IllegalArgumentException("Unsupported char symbol [" + ((char) c) + "]");
            }
        }
        if (sb.length() != 0) {
            list.add(sb.toString().toCharArray());
        }
        char[][] chars = list.toArray(new char[0][0]);
        int hash = 1;
        for (char[] cs : chars) hash = 31 * hash + Arrays.hashCode(cs);
        return new StringToken(chars, hash, formatType);
    }

    private final char[][] parts;
    private final int hash;
    private final AtomicReference<FormatType> formatTypeReference;

    private StringToken(char[][] parts, int hash, AtomicReference<FormatType> formatTypeReference) {
        this.parts = parts;
        this.hash = hash;
        this.formatTypeReference = formatTypeReference;
    }

    public AtomicReference<FormatType> getFormatTypeReference() {
        return formatTypeReference;
    }

    public String toUpperKebabCase() {
        return this.toKebabCase(true);
    }

    public String toUpperUnderLine() {
        return this.toUnderLine(true);
    }

    public String toUpperCamelCase() {
        return this.toCamelCase(true);
    }

    public String toLowerKebabCase() {
        return this.toKebabCase(false);
    }

    public String toLowerUnderLine() {
        return this.toUnderLine(false);
    }

    public String toLowerCamelCase() {
        return this.toCamelCase(false);
    }

    private String toKebabCase(boolean upper) {
        return marge("-", upper, upper);
    }

    private String toUnderLine(boolean upper) {
        return marge("_", upper, upper);
    }

    private String toCamelCase(boolean upper) {
        return marge(null, false, upper ? true : null);
    }

    private String marge(String delimiter, boolean allUpper, Boolean headUpper) {
        boolean isCamelCase = !allUpper && (headUpper == null || headUpper);
        StringBuilder sb = new StringBuilder(parts.length * (parts[0].length + 6));

        if (isCamelCase && Character.isUpperCase(parts[0][0])) {
            sb.append(parts[0]);
        } else {
            sb.append(c(parts[0][0], headUpper != null && headUpper));
            for (int i = 1; i < parts[0].length; i++) {
                sb.append(c(parts[0][i], allUpper));
            }
        }

        for (int x = 1; x < parts.length; x++) {
            if (delimiter != null) sb.append(delimiter);
            if (isCamelCase && Character.isUpperCase(parts[x][0])) {
                sb.append(parts[x]);
            } else {
                sb.append(c(parts[x][0], headUpper == null || headUpper));
                for (int i = 1; i < parts[x].length; i++) {
                    sb.append(c(parts[x][i], allUpper));
                }
            }
        }
        return sb.toString();
    }

    private char c(char c, boolean upper) {
        return upper ? Character.toUpperCase(c) : Character.toLowerCase(c);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof StringToken)) return false;
        StringToken that = (StringToken) object;
        int length = this.parts.length;
        if (length != that.parts.length) return false;
        for (int i = 0; i < length; i++) {
            if (!parts[i].equals(that.parts[i])) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public String toString() {
        if (formatTypeReference != null) {
            FormatType formatType = formatTypeReference.get();
            switch (formatType) {
                case UpperCamelCase:
                    return toUpperCamelCase();
                case LowerCamelCase:
                    return toLowerCamelCase();
                case UpperUnderLine:
                    return toUpperUnderLine();
                case LowerUnderLine:
                    return toLowerUnderLine();
                case UpperKebabCase:
                    return toUpperKebabCase();
                case LowerKebabCase:
                    return toLowerKebabCase();
            }
        }
        return marge("", false, false);
    }

    public enum FormatType {
        UpperKebabCase,
        UpperUnderLine,
        UpperCamelCase,
        LowerKebabCase,
        LowerUnderLine,
        LowerCamelCase,
    }
}
