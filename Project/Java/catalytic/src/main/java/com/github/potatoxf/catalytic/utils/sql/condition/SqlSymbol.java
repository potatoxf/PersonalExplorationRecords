/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.sql.condition;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * SQL符号
 *
 * @author potatoxf
 */
public abstract class SqlSymbol {

    /**
     * 默认数据库
     */
    protected static final String DEFAULT_DATABASE = null;
    protected static final Object IGNORED_VALUE = Void.class;

    /**
     * 默认符号
     */
    private final String defaultSymbol;

    /**
     * 默认构造函数
     *
     * @param defaultSymbol 默认符号
     */
    protected SqlSymbol(String defaultSymbol) {
        this.defaultSymbol = defaultSymbol;
    }

    /**
     * 获取符号
     *
     * @return 返回符号
     */
    public final String symbol() {
        return symbol(null);
    }

    /**
     * 获取符号
     *
     * @param database 数据库类型
     * @return 返回符号
     */
    public String symbol(String database) {
        return defaultSymbol;
    }

    /**
     * 合法性列名称
     *
     * @param columnName 列名称
     * @return 返回合法性列名称
     */
    public String legitimateColumnName(String columnName) {
        if (columnName == null) {
            throw new IllegalArgumentException("The column name must be not null");
        }
        columnName = columnName.trim();
        if (columnName.isEmpty()) {
            throw new IllegalArgumentException("The column name must be not empty");
        }
        int[] codePoints = columnName.codePoints().toArray();
        char[] chars = Character.toChars(codePoints[0]);
        if (chars.length != 1) {
            throw new IllegalArgumentException("Not support character '" + String.valueOf(chars) + "'");
        }
        char c = chars[0];
        if (!(Character.isLetter(c) || c == '_')) {
            if (Character.isDigit(c)) {
                throw new IllegalArgumentException("The first letter cannot be a number '" + c + "'");
            } else {
                throw new IllegalArgumentException("Not support character '" + c + "'");
            }
        }
        int x = 0;
        for (int i = 1; i < codePoints.length; i++) {
            chars = Character.toChars(codePoints[i]);
            if (chars.length != 1) {
                throw new IllegalArgumentException("Not support character '" + String.valueOf(chars) + "'");
            }
            c = chars[0];
            if (Character.isLetterOrDigit(c) || c == '_') {
                if (c == '_') {
                    if (x != 0) {
                        throw new IllegalArgumentException("The '" + c + "' Symbols cannot be continuous");
                    }
                    x++;
                } else {
                    x = 0;
                }
            } else {
                throw new IllegalArgumentException("Not support character '" + c + "'");
            }
        }
        return columnName.toUpperCase();
    }

    /**
     * 生成表达式
     *
     * @param database   数据库类型
     * @param args       参数容器
     * @param columnName 列名称
     * @param value      值
     * @return 返回生成表达式
     */
    protected final String generateConditionExpression(String database, List<? super Object> args, String columnName, Object value) {
        if (value == null) return "";
        String s = " " + legitimateColumnName(columnName) + " " + symbol(database);
        if (value != IGNORED_VALUE) {
            s += " ";
            value = handleValue(value);
            if (args != null) args.add(value);
            s += handlePlaceholder(database, args != null, value);
        }
        return s;
    }

    /**
     * 生成表达式
     *
     * @param database   数据库类型
     * @param args       参数容器
     * @param columnName 列名称
     * @param values     多个值
     * @return 返回生成表达式
     */
    protected final String generateConditionExpressions(String database, List<? super Object> args, String columnName, Object[] values) {
        values = Arrays.stream(values).filter(Objects::nonNull).toArray();
        if (values.length == 0) return "";
        boolean placeholder = args != null;
        StringBuilder s = new StringBuilder(20);
        s.append(" ").append(legitimateColumnName(columnName)).append(" ").append(symbol(database)).append(" (");
        Object value = handleValue(values[0]);
        if (placeholder) args.add(value);
        s.append(handlePlaceholder(database, placeholder, value));
        for (int i = 1; i < values.length; i++) {
            s.append(",");
            value = handleValue(values[i]);
            if (placeholder) args.add(value);
            s.append(handlePlaceholder(database, placeholder, value));

        }
        s.append(")");
        return s.toString();
    }

    /**
     * 处理占位字符串
     *
     * @param database    数据库类型
     * @param placeholder 是否式占位符
     * @param value       值
     * @return 返回处理后占位字符串
     */
    protected String handlePlaceholder(String database, boolean placeholder, Object value) {
        if (placeholder) {
            return "?";
        } else {
            if (value instanceof String) {
                return "'" + value + "'";
            } else {
                return String.valueOf(value);
            }
        }
    }

    /**
     * 处理值
     *
     * @param value 值
     * @return 返回值
     */
    public Object handleValue(Object value) {
        return value;
    }

}
