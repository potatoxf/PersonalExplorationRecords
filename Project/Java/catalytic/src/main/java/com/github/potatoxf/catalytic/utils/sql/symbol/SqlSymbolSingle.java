/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.sql.symbol;

/**
 * SQL符号,单元
 *
 * @author potatoxf
 */
public class SqlSymbolSingle extends SqlSymbol {

    protected SqlSymbolSingle(String defaultSymbol) {
        super(defaultSymbol);
    }

    /**
     * 打印关系条件
     *
     * @param columnName 列名称
     * @return 返回关系条件字符串
     */
    public final String print(String columnName) {
        return print(SqlSymbol.DEFAULT_DATABASE, columnName);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param columnName 列名称
     * @return 返回关系条件字符串
     */
    public String print(String database, String columnName) {
        return " " + legitimateColumnName(columnName) + " " + symbol(database) + " ";
    }
}
