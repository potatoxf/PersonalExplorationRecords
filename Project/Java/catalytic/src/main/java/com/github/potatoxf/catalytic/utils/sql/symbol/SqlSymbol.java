/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.sql.symbol;

/**
 * SQL符号
 *
 * @author potatoxf
 */
public abstract class SqlSymbol {

    protected static final String DEFAULT_DATABASE = null;

    /**
     * 获取符号
     *
     * @return 返回符号
     */
    public final String symbol() {
        return symbol(null);
    }

    /**
     * 获取符号
     *
     * @param database 数据库类型
     * @return 返回符号
     */
    public abstract String symbol(String database);
}
