/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.sql.condition;

import java.util.Date;
import java.util.List;

/**
 * SQL符号,二元
 *
 * @author potatoxf
 */
public class SqlSymbolOfBinary extends SqlSymbol {

    protected SqlSymbolOfBinary(String defaultSymbol) {
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
        return doPrint(database, null, columnName, value);
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
        return doPrint(database, null, columnName, value);
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
        return doPrint(database, args, columnName, value);
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
        return doPrint(database, args, columnName, value);
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
        return doPrint(database, args, columnName, value);
    }
    protected String doPrint(String database, List<? super Object> args, String columnName, Object value) {
        return generateConditionExpression(database, args, columnName, value);
    }
}
