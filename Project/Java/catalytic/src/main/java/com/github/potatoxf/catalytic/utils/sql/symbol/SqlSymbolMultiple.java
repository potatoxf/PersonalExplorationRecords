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
 * SQL符号，三元
 *
 * @author potatoxf
 */
public abstract class SqlSymbolMultiple extends SqlSymbol {

    /**
     * 打印关系条件
     *
     * @param name   名称
     * @param values 值
     * @return 返回关系条件字符串
     */
    public final String print(String name, Number... values) {
        return print(SqlSymbol.DEFAULT_DATABASE, name, values);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param name     名称
     * @param values   值
     * @return 返回关系条件字符串
     */
    public String print(String database, String name, Number... values) {
        return handle(database, name, Arrays.stream(values).filter(Objects::nonNull).map(Object::toString).collect(Collectors.joining(",")));
    }

    /**
     * 打印关系条件
     *
     * @param name   名称
     * @param values 值
     * @return 返回关系条件字符串
     */
    public final String print(String name, String... values) {
        return print(SqlSymbol.DEFAULT_DATABASE, name, values);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param name     名称
     * @param values   值
     * @return 返回关系条件字符串
     */
    public String print(String database, String name, String... values) {
        return handle(database, name, Arrays.stream(values).filter(Objects::nonNull).map(o -> "'" + o + "'").collect(Collectors.joining(",")));
    }

    /**
     * 打印关系条件
     *
     * @param name   名称
     * @param values 值
     * @return 返回关系条件字符串
     */
    public final String print(String name, Date... values) {
        return print(SqlSymbol.DEFAULT_DATABASE, name, values);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param name     名称
     * @param values   值
     * @return 返回关系条件字符串
     */
    public String print(String database, String name, Date... values) {
        return handle(database, name, Arrays.stream(values).filter(Objects::nonNull).map(o -> "'" + o + "'").collect(Collectors.joining(",")));
    }

    /**
     * 打印关系条件
     *
     * @param args   参数容器
     * @param name   名称
     * @param values 值
     * @return 返回关系条件字符串
     */
    public final String print(List<? super Object> args, String name, Number... values) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, name, values);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param args     参数容器
     * @param name     名称
     * @param values   值
     * @return 返回关系条件字符串
     */
    public String print(String database, List<? super Object> args, String name, Number... values) {
        return handle(database, name, args, Arrays.stream(values).filter(Objects::nonNull).collect(Collectors.toList()));
    }

    /**
     * 打印关系条件
     *
     * @param args   参数容器
     * @param name   名称
     * @param values 值
     * @return 返回关系条件字符串
     */
    public final String print(List<? super Object> args, String name, String... values) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, name, values);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param args     参数容器
     * @param name     名称
     * @param values   值
     * @return 返回关系条件字符串
     */
    public String print(String database, List<? super Object> args, String name, String... values) {
        return handle(database, name, args, Arrays.stream(values).filter(Objects::nonNull).collect(Collectors.toList()));
    }

    /**
     * 打印关系条件
     *
     * @param args   参数容器
     * @param name   名称
     * @param values 值
     * @return 返回关系条件字符串
     */
    public final String print(List<? super Object> args, String name, Date... values) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, name, values);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param args     参数容器
     * @param name     名称
     * @param values   值
     * @return 返回关系条件字符串
     */
    public String print(String database, List<? super Object> args, String name, Date... values) {
        return handle(database, name, args, Arrays.stream(values).filter(Objects::nonNull).collect(Collectors.toList()));
    }

    private String handle(String database, String name, String value) {
        if (value.isEmpty()) {
            return null;
        } else {
            return name + " " + symbol(database) + " (" + value + ")";
        }
    }

    private String handle(String database, String name, List<? super Object> args, List<? extends Object> list) {
        if (list.isEmpty()) return null;
        args.addAll(list);
        String value = list.stream().map(o -> "?").collect(Collectors.joining(","));
        return name + " " + symbol(database) + " (" + value + ")";
    }
}
