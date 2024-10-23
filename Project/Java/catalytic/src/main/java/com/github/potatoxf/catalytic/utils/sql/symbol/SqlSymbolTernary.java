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

    protected SqlSymbolTernary(String defaultSymbol) {
        super(defaultSymbol);
    }

    /**
     * 打印关系条件
     *
     * @param columnName 名称
     * @param value1     第一值
     * @param value2     第二值
     * @return 返回关系条件字符串
     */
    public final String print(String columnName, Number value1, Number value2) {
        return print(SqlSymbol.DEFAULT_DATABASE, columnName, value1, value2);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param columnName 列名称
     * @param value1     第一值
     * @param value2     第二值
     * @return 返回关系条件字符串
     */
    public abstract String print(String database, String columnName, Number value1, Number value2);

    /**
     * 打印关系条件
     *
     * @param columnName 名称
     * @param value1     第一值
     * @param value2     第二值
     * @return 返回关系条件字符串
     */
    public final String print(String columnName, String value1, String value2) {
        return print(SqlSymbol.DEFAULT_DATABASE, columnName, value1, value2);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param columnName 列名称
     * @param value1     第一值
     * @param value2     第二值
     * @return 返回关系条件字符串
     */
    public abstract String print(String database, String columnName, String value1, String value2);

    /**
     * 打印关系条件
     *
     * @param columnName 名称
     * @param value1     第一值
     * @param value2     第二值
     * @return 返回关系条件字符串
     */
    public final String print(String columnName, Date value1, Date value2) {
        return print(SqlSymbol.DEFAULT_DATABASE, columnName, value1, value2);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param columnName 列名称
     * @param value1     第一值
     * @param value2     第二值
     * @return 返回关系条件字符串
     */
    public abstract String print(String database, String columnName, Date value1, Date value2);

    /**
     * 打印关系条件
     *
     * @param args       参数容器
     * @param columnName 名称
     * @param value1     第一值
     * @param value2     第二值
     * @return 返回关系条件字符串
     */
    public final String print(List<? super Object> args, String columnName, Number value1, Number value2) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, columnName, value1, value2);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param args       参数容器
     * @param columnName 列名称
     * @param value1     第一值
     * @param value2     第二值
     * @return 返回关系条件字符串
     */
    public abstract String print(String database, List<? super Object> args, String columnName, Number value1, Number value2);

    /**
     * 打印关系条件
     *
     * @param args       参数容器
     * @param columnName 名称
     * @param value1     第一值
     * @param value2     第二值
     * @return 返回关系条件字符串
     */
    public final String print(List<? super Object> args, String columnName, String value1, String value2) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, columnName, value1, value2);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param args       参数容器
     * @param columnName 列名称
     * @param value1     第一值
     * @param value2     第二值
     * @return 返回关系条件字符串
     */
    public abstract String print(String database, List<? super Object> args, String columnName, String value1, String value2);

    /**
     * 打印关系条件
     *
     * @param args       参数容器
     * @param columnName 名称
     * @param value1     第一值
     * @param value2     第二值
     * @return 返回关系条件字符串
     */
    public final String print(List<? super Object> args, String columnName, Date value1, Date value2) {
        return print(SqlSymbol.DEFAULT_DATABASE, args, columnName, value1, value2);
    }

    /**
     * 打印关系条件
     *
     * @param database   数据库类型
     * @param args       参数容器
     * @param columnName 列名称
     * @param value1     第一值
     * @param value2     第二值
     * @return 返回关系条件字符串
     */
    public abstract String print(String database, List<? super Object> args, String columnName, Date value1, Date value2);
}
