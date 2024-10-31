/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.sql.condition;

import java.util.Date;
import java.util.List;

/**
 * SQL三元符号，多个值
 *
 * @author potatoxf
 */
public class SqlSymbolOfMultiple extends SqlSymbolOfBinary {

    protected SqlSymbolOfMultiple(String defaultSymbol) {
        super(defaultSymbol);
    }

    /**
     * 打印关系条件
     *
     * @param columnName 列名称
     * @param values     值
     * @return 返回关系条件字符串
     */
    public final String print(String columnName, Number... values) {
        return print(SqlSymbol.DEFAULT_DATABASE, columnName, values);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param columnName 列名称
     * @param values     值
     * @return 返回关系条件字符串
     */
    public String print(String database, String columnName, Number... values) {
        return doPrint(database, null, columnName, values);
    }

    /**
     * 打印关系条件
     *
     * @param columnName 列名称
     * @param values     值
     * @return 返回关系条件字符串
     */
    public final String print(String columnName, String... values) {
        return print(SqlSymbol.DEFAULT_DATABASE, columnName, values);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param columnName 列名称
     * @param values     值
     * @return 返回关系条件字符串
     */
    public String print(String database, String columnName, String... values) {
        return doPrint(database, null, columnName, values);
    }

    /**
     * 打印关系条件
     *
     * @param args       参数容器
     * @param columnName 列名称
     * @param values     值
     * @return 返回关系条件字符串
     */
    public final String print(List<? super Object> args, String columnName, Number... values) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, columnName, values);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param args       参数容器
     * @param columnName 列名称
     * @param values     值
     * @return 返回关系条件字符串
     */
    public String print(String database, List<? super Object> args, String columnName, Number... values) {
        return doPrint(database, args, columnName, values);
    }

    /**
     * 打印关系条件
     *
     * @param args       参数容器
     * @param columnName 列名称
     * @param values     值
     * @return 返回关系条件字符串
     */
    public final String print(List<? super Object> args, String columnName, String... values) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, columnName, values);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param args       参数容器
     * @param columnName 列名称
     * @param values     值
     * @return 返回关系条件字符串
     */
    public String print(String database, List<? super Object> args, String columnName, String... values) {
        return doPrint(database, args, columnName, values);
    }

    /**
     * 打印关系条件
     *
     * @param args       参数容器
     * @param columnName 列名称
     * @param values     值
     * @return 返回关系条件字符串
     */
    public final String print(List<? super Object> args, String columnName, Date... values) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, columnName, values);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param args       参数容器
     * @param columnName 列名称
     * @param values     值
     * @return 返回关系条件字符串
     */
    public String print(String database, List<? super Object> args, String columnName, Date... values) {
        return doPrint(database, args, columnName, values);
    }

    @Override
    protected String doPrint(String database, List<? super Object> args, String columnName, Object value) {
        return generateConditionExpressions(database, args, columnName, new Object[]{value});
    }

    protected String doPrint(String database, List<? super Object> args, String columnName, Object[] values) {
        return generateConditionExpressions(database, args, columnName, values);
    }
}
