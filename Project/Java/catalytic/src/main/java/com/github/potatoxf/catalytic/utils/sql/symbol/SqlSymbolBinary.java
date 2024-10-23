/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.sql.symbol;

import java.util.Date;
import java.util.List;

/**
 * SQL符号,二元
 *
 * @author potatoxf
 */
public class SqlSymbolBinary extends SqlSymbol {

    protected SqlSymbolBinary(String defaultSymbol) {
        super(defaultSymbol);
    }

    /**
     * 打印关系条件
     *
     * @param columnName 列名称
     * @param value      值
     * @return 返回关系条件字符串
     */
    public final String print(String columnName, Number value) {
        return print(SqlSymbol.DEFAULT_DATABASE, columnName, value);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param columnName 列名称
     * @param value      值
     * @return 返回关系条件字符串
     */
    public String print(String database, String columnName, Number value) {
        return handle(database, columnName, value);
    }

    /**
     * 打印关系条件
     *
     * @param columnName 列名称
     * @param value      值
     * @return 返回关系条件字符串
     */
    public final String print(String columnName, String value) {
        return print(SqlSymbol.DEFAULT_DATABASE, columnName, value);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param columnName 列名称
     * @param value      值
     * @return 返回关系条件字符串
     */
    public String print(String database, String columnName, String value) {
        return handle(database, columnName, value);
    }

    /**
     * 打印关系条件
     *
     * @param columnName 列名称
     * @param value      值
     * @return 返回关系条件字符串
     */
    public final String print(String columnName, Date value) {
        return print(SqlSymbol.DEFAULT_DATABASE, columnName, value);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param columnName 列名称
     * @param value      值
     * @return 返回关系条件字符串
     */
    public String print(String database, String columnName, Date value) {
        return handle(database, columnName, value);
    }

    /**
     * 打印关系条件
     *
     * @param args       参数容器
     * @param columnName 列名称
     * @param value      值
     * @return 返回关系条件字符串
     */
    public final String print(List<? super Object> args, String columnName, Number value) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, columnName, value);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param args       参数容器
     * @param columnName 列名称
     * @param value      值
     * @return 返回关系条件字符串
     */
    public String print(String database, List<? super Object> args, String columnName, Number value) {
        return handle(database, args, columnName, value);
    }

    /**
     * 打印关系条件
     *
     * @param args       参数容器
     * @param columnName 列名称
     * @param value      值
     * @return 返回关系条件字符串
     */
    public final String print(List<? super Object> args, String columnName, String value) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, columnName, value);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param args       参数容器
     * @param columnName 列名称
     * @param value      值
     * @return 返回关系条件字符串
     */
    public String print(String database, List<? super Object> args, String columnName, String value) {
        return handle(database, args, columnName, value);
    }

    /**
     * 打印关系条件
     *
     * @param args       参数容器
     * @param columnName 列名称
     * @param value      值
     * @return 返回关系条件字符串
     */
    public final String print(List<? super Object> args, String columnName, Date value) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, columnName, value);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param args       参数容器
     * @param columnName 列名称
     * @param value      值
     * @return 返回关系条件字符串
     */
    public String print(String database, List<? super Object> args, String columnName, Date value) {
        return handle(database, args, columnName, value);
    }

    protected String handle(String database, List<? super Object> args, String columnName, Object value) {
        args.add(value);
        return " " + legitimateColumnName(columnName) + " " + symbol(database) + " ? ";
    }

    protected String handle(String database, String columnName, Object value) {
        return " " + legitimateColumnName(columnName) + " " + symbol(database) + " " + value + " ";
    }
}
