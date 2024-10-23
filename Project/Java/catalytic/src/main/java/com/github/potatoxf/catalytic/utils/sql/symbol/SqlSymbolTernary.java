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
public abstract class SqlSymbolTernary extends SqlSymbol {

    /**
     * 打印关系条件
     *
     * @param name   名称
     * @param value1 第一值
     * @param value2 第二值
     * @return 返回关系条件字符串
     */
    public final String print(String name, Number value1, Number value2) {
        return print(SqlSymbol.DEFAULT_DATABASE, name, value1, value2);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param name     名称
     * @param value1   第一值
     * @param value2   第二值
     * @return 返回关系条件字符串
     */
    public String print(String database, String name, Number value1, Number value2) {
        return null;
    }

    /**
     * 打印关系条件
     *
     * @param name   名称
     * @param value1 第一值
     * @param value2 第二值
     * @return 返回关系条件字符串
     */
    public final String print(String name, String value1, String value2) {
        return print(SqlSymbol.DEFAULT_DATABASE, name, value1, value2);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param name     名称
     * @param value1   第一值
     * @param value2   第二值
     * @return 返回关系条件字符串
     */
    public String print(String database, String name, String value1, String value2) {
        return null;
    }

    /**
     * 打印关系条件
     *
     * @param name   名称
     * @param value1 第一值
     * @param value2 第二值
     * @return 返回关系条件字符串
     */
    public final String print(String name, Date value1, Date value2) {
        return print(SqlSymbol.DEFAULT_DATABASE, name, value1, value2);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param name     名称
     * @param value1   第一值
     * @param value2   第二值
     * @return 返回关系条件字符串
     */
    public String print(String database, String name, Date value1, Date value2) {
        return null;
    }

    /**
     * 打印关系条件
     *
     * @param args   参数容器
     * @param name   名称
     * @param value1 第一值
     * @param value2 第二值
     * @return 返回关系条件字符串
     */
    public final String print(List<? super Object> args, String name, Number value1, Number value2) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, name, value1, value2);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param args     参数容器
     * @param name     名称
     * @param value1   第一值
     * @param value2   第二值
     * @return 返回关系条件字符串
     */
    public String print(String database, List<? super Object> args, String name, Number value1, Number value2) {
        return null;
    }

    /**
     * 打印关系条件
     *
     * @param args   参数容器
     * @param name   名称
     * @param value1 第一值
     * @param value2 第二值
     * @return 返回关系条件字符串
     */
    public final String print(List<? super Object> args, String name, String value1, String value2) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, name, value1, value2);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param args     参数容器
     * @param name     名称
     * @param value1   第一值
     * @param value2   第二值
     * @return 返回关系条件字符串
     */
    public String print(String database, List<? super Object> args, String name, String value1, String value2) {
        return null;
    }

    /**
     * 打印关系条件
     *
     * @param args   参数容器
     * @param name   名称
     * @param value1 第一值
     * @param value2 第二值
     * @return 返回关系条件字符串
     */
    public final String print(List<? super Object> args, String name, Date value1, Date value2) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, name, value1, value2);
    }

    /**
     * 打印关系条件
     *
     * @param database 数据库类型
     * @param args     参数容器
     * @param name     名称
     * @param value1   第一值
     * @param value2   第二值
     * @return 返回关系条件字符串
     */
    public String print(String database, List<? super Object> args, String name, Date value1, Date value2) {
        return null;
    }
}
