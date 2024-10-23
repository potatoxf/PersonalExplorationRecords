/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.sql.symbol;

import java.util.Date;
import java.util.List;

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

    /**
     * 默认符号
     */
    private final String defaultSymbol;

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
        return columnName;
    }

    /**
     * 处理值
     *
     * @param value 值
     * @param <T>   值类型
     * @return 返回值
     */
    public <T> T handleValue(T value) {
        return value;
    }

    /**
     * SQL符号实例集
     */
    public static final class SqlSymbolInstances {
        public static final SqlSymbolSingle IS_NULL = new SqlSymbolSingle("IS NULL");
        public static final SqlSymbolSingle IS_NOT_NULL = new SqlSymbolSingle("IS NOT NULL");
        public static final SqlSymbolBinary EQ = new SqlSymbolBinary("=");
        public static final SqlSymbolBinary NOT_EQ = new SqlSymbolBinary("!=") {
            @Override
            public String symbol(String database) {
                if (database != null) {
                    if (database.toLowerCase().contains("oracle")) {
                        return "<>";
                    }
                }
                return super.symbol(database);
            }
        };
        public static final SqlSymbolBinary GT = new SqlSymbolBinary(">");
        public static final SqlSymbolBinary GT_EQ = new SqlSymbolBinary(">=");
        public static final SqlSymbolBinary LT = new SqlSymbolBinary("<");
        public static final SqlSymbolBinary LT_EQ = new SqlSymbolBinary("<=");
        public static final SqlSymbolBinary LIKE = new SqlSymbolBinary("LIKE");
        public static final SqlSymbolBinary NOT_LIKE = new SqlSymbolBinary("NOT LIKE");
        public static final SqlSymbolBinary LEFT_LIKE = new SqlSymbolBinary("LIKE");
        public static final SqlSymbolBinary NOT_LEFT_LIKE = new SqlSymbolBinary("NOT LIKE");
        public static final SqlSymbolBinary RIGHT_LIKE = new SqlSymbolBinary("LIKE");
        public static final SqlSymbolBinary NOT_RIGHT_LIKE = new SqlSymbolBinary("NOT LIKE");
        public static final SqlSymbolTernary BETWEEN_AND = new SqlSymbolTernary("BETWEEN") {

            @Override
            public String print(String database, String columnName, Number value1, Number value2) {
                return " " + legitimateColumnName(columnName) + " " + symbol(database) + " " + value1 + " AND " + value2 + " ";
            }

            @Override
            public String print(String database, String columnName, String value1, String value2) {
                return " " + legitimateColumnName(columnName) + " " + symbol(database) + " '" + value1 + "' AND '" + value2 + "' ";
            }

            @Override
            public String print(String database, String columnName, Date value1, Date value2) {
                return " " + legitimateColumnName(columnName) + " " + symbol(database) + " '" + value1 + "' AND '" + value2 + "' ";
            }

            @Override
            public String print(String database, List<? super Object> args, String columnName, Number value1, Number value2) {
                args.add(value1);
                args.add(value2);
                return " " + legitimateColumnName(columnName) + " " + symbol(database) + " ? AND ? ";
            }

            @Override
            public String print(String database, List<? super Object> args, String columnName, String value1, String value2) {
                args.add(value1);
                args.add(value2);
                return " " + legitimateColumnName(columnName) + " " + symbol(database) + " ? AND ? ";
            }

            @Override
            public String print(String database, List<? super Object> args, String columnName, Date value1, Date value2) {
                args.add(value1);
                args.add(value2);
                return " " + legitimateColumnName(columnName) + " " + symbol(database) + " ? AND ? ";
            }
        };
        public static final SqlSymbolMultiple IN = new SqlSymbolMultiple("IN");
        public static final SqlSymbolMultiple NOT_IN = new SqlSymbolMultiple("NOT IN");
        public static final SqlSymbolMultiple NAMES = new SqlSymbolMultiple("");
        public static final SqlSymbolMultiple VALUES = new SqlSymbolMultiple("VALUES");

        private SqlSymbolInstances() {
        }
    }
}
