/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.sql.symbol;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * SQL三元符号，多个值
 *
 * @author potatoxf
 */
public class SqlSymbolMultiple extends SqlSymbolBinary {

    protected SqlSymbolMultiple(String defaultSymbol) {
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
        return handle(database, columnName, Arrays.stream(values).filter(Objects::nonNull).map(Object::toString).collect(Collectors.joining(",")));
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
        return handle(database, columnName, Arrays.stream(values).filter(Objects::nonNull).map(o -> "'" + o + "'").collect(Collectors.joining(",")));
    }

    /**
     * 打印关系条件
     *
     * @param columnName 列名称
     * @param values     值
     * @return 返回关系条件字符串
     */
    public final String print(String columnName, Date... values) {
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
    public String print(String database, String columnName, Date... values) {
        return handle(database, columnName, Arrays.stream(values).filter(Objects::nonNull).map(o -> "'" + o + "'").collect(Collectors.joining(",")));
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
        return handle(database, args, columnName, Arrays.stream(values).filter(Objects::nonNull).collect(Collectors.toList()));
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
        return handle(database, args, columnName, Arrays.stream(values).filter(Objects::nonNull).collect(Collectors.toList()));
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
        return handle(database, args, columnName, Arrays.stream(values).filter(Objects::nonNull).collect(Collectors.toList()));
    }

    protected String handle(String database, String columnName, String value) {
        if (value.isEmpty()) {
            return null;
        } else {
            return " " + columnName + " " + symbol(database) + " (" + value + ") ";
        }
    }

    protected String handle(String database, List<? super Object> args, String columnName, List<? extends Object> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            args.addAll(list);
            String value = list.stream().map(o -> "?").collect(Collectors.joining(","));
            return " " + columnName + " " + symbol(database) + " (" + value + ") ";
        }
    }

    @Override
    protected String handle(String database, String columnName, Object value) {
        return " " + legitimateColumnName(columnName) + " " + symbol(database) + " (" + value + ") ";
    }

    @Override
    protected String handle(String database, List<? super Object> args, String columnName, Object value) {
        args.add(value);
        return " " + legitimateColumnName(columnName) + " " + symbol(database) + " (?) ";
    }
}
