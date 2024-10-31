/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.sql.condition;

import java.util.Date;
import java.util.List;

/**
 * SQL符号实例集
 *
 * @author potatoxf
 */
public final class SqlSymbols {
    public static final SqlSymbolOfSingle IS_NULL = new SqlSymbolOfSingle("IS NULL");
    public static final SqlSymbolOfSingle IS_NOT_NULL = new SqlSymbolOfSingle("IS NOT NULL");
    public static final SqlSymbolOfBinary EQ = new SqlSymbolOfBinary("=");
    public static final SqlSymbolOfBinary NOT_EQ = new SqlSymbolOfBinary("!=") {
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
    public static final SqlSymbolOfBinary GT = new SqlSymbolOfBinary(">");
    public static final SqlSymbolOfBinary GT_EQ = new SqlSymbolOfBinary(">=");
    public static final SqlSymbolOfBinary LT = new SqlSymbolOfBinary("<");
    public static final SqlSymbolOfBinary LT_EQ = new SqlSymbolOfBinary("<=");
    public static final SqlSymbolOfBinary LIKE = new SqlSymbolOfBinary("LIKE") {
        @Override
        public Object handleValue(Object value) {
            return "%" + value + "%";
        }
    };
    public static final SqlSymbolOfBinary NOT_LIKE = new SqlSymbolOfBinary("NOT LIKE") {
        @Override
        public Object handleValue(Object value) {
            return "%" + value + "%";
        }
    };
    public static final SqlSymbolOfBinary LEFT_LIKE = new SqlSymbolOfBinary("LIKE") {
        @Override
        public Object handleValue(Object value) {
            return "%" + value;
        }
    };
    public static final SqlSymbolOfBinary NOT_LEFT_LIKE = new SqlSymbolOfBinary("NOT LIKE") {
        @Override
        public Object handleValue(Object value) {
            return "%" + value;
        }
    };
    public static final SqlSymbolOfBinary RIGHT_LIKE = new SqlSymbolOfBinary("LIKE") {
        @Override
        public Object handleValue(Object value) {
            return value + "%";
        }
    };
    public static final SqlSymbolOfBinary NOT_RIGHT_LIKE = new SqlSymbolOfBinary("NOT LIKE") {
        @Override
        public Object handleValue(Object value) {
            return value + "%";
        }
    };
    public static final SqlSymbolOfTernary BETWEEN_AND = new SqlSymbolOfTernary("BETWEEN") {

        @Override
        public String print(String database, String columnName, Number value1, Number value2) {
            return " " + legitimateColumnName(columnName) + " " + symbol(database) + " " + value1 + " AND " + value2 + " ";
        }

        @Override
        public String print(String database, String columnName, String value1, String value2) {
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
    public static final SqlSymbolOfMultiple IN = new SqlSymbolOfMultiple("IN");
    public static final SqlSymbolOfMultiple NOT_IN = new SqlSymbolOfMultiple("NOT IN");
    public static final SqlSymbolOfMultiple NAMES = new SqlSymbolOfMultiple("");
    public static final SqlSymbolOfMultiple VALUES = new SqlSymbolOfMultiple("VALUES");

    private SqlSymbols() {
    }
}
