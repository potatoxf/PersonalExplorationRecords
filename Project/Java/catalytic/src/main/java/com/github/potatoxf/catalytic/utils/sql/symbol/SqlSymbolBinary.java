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
public abstract class SqlSymbolBinary extends SqlSymbol {
    /**
     * 打印关系条件
     *
     * @param name  名称
     * @param value 值
     * @return 返回关系条件字符串
     */
    public final String print(String name, Number value) {
        return print(SqlSymbol.DEFAULT_DATABASE, name, value);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param name     名称
     * @param value    值
     * @return 返回关系条件字符串
     */
    public String print(String database, String name, Number value) {
        return null;
    }

    /**
     * 打印关系条件
     *
     * @param name  名称
     * @param value 值
     * @return 返回关系条件字符串
     */
    public final String print(String name, String value) {
        return print(SqlSymbol.DEFAULT_DATABASE, name, value);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param name     名称
     * @param value    值
     * @return 返回关系条件字符串
     */
    public String print(String database, String name, String value) {
        return null;
    }

    /**
     * 打印关系条件
     *
     * @param name  名称
     * @param value 值
     * @return 返回关系条件字符串
     */
    public final String print(String name, Date value) {
        return print(SqlSymbol.DEFAULT_DATABASE, name, value);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param name     名称
     * @param value    值
     * @return 返回关系条件字符串
     */
    public String print(String database, String name, Date value) {
        return null;
    }

    /**
     * 打印关系条件
     *
     * @param args  参数容器
     * @param name  名称
     * @param value 值
     * @return 返回关系条件字符串
     */
    public final String print(List<? extends Object> args, String name, Number value) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, name, value);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param args     参数容器
     * @param name     名称
     * @param value    值
     * @return 返回关系条件字符串
     */
    public String print(String database, List<? extends Object> args, String name, Number value) {
        return null;
    }

    /**
     * 打印关系条件
     *
     * @param args  参数容器
     * @param name  名称
     * @param value 值
     * @return 返回关系条件字符串
     */
    public final String print(List<? extends Object> args, String name, String value) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, name, value);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param args     参数容器
     * @param name     名称
     * @param value    值
     * @return 返回关系条件字符串
     */
    public String print(String database, List<? extends Object> args, String name, String value) {
        return null;
    }

    /**
     * 打印关系条件
     *
     * @param args  参数容器
     * @param name  名称
     * @param value 值
     * @return 返回关系条件字符串
     */
    public final String print(List<? extends Object> args, String name, Date value) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, name, value);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param args     参数容器
     * @param name     名称
     * @param value    值
     * @return 返回关系条件字符串
     */
    public String print(String database, List<? extends Object> args, String name, Date value) {
        return null;
    }
}
