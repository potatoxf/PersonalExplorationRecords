/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.sql.symbol;

import java.util.Date;
import java.util.List;

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
        return null;
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
        return null;
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
        return null;
    }

    /**
     * 打印关系条件
     *
     * @param args   参数容器
     * @param name   名称
     * @param values 值
     * @return 返回关系条件字符串
     */
    public final String print(List<? extends Object> args, String name, Number... values) {
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
    public String print(String database, List<? extends Object> args, String name, Number... values) {
        return null;
    }

    /**
     * 打印关系条件
     *
     * @param args   参数容器
     * @param name   名称
     * @param values 值
     * @return 返回关系条件字符串
     */
    public final String print(List<? extends Object> args, String name, String... values) {
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
    public String print(String database, List<? extends Object> args, String name, String... values) {
        return null;
    }

    /**
     * 打印关系条件
     *
     * @param args   参数容器
     * @param name   名称
     * @param values 值
     * @return 返回关系条件字符串
     */
    public final String print(List<? extends Object> args, String name, Date... values) {
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
    public String print(String database, List<? extends Object> args, String name, Date... values) {
        return null;
    }
}
