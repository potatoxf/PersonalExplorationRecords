/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * 基础日期时间转换器
 *
 * @author potatoxf
 */
public abstract class BasicWhenConverterImpl<Origin, Target> extends BasicWhenConverter<Origin, Target, BasicWhenConverterInput> {

    public static String ofString(TemporalAccessor value, BasicWhenConverterInput input) {
        DateTimeFormatter dateTimeFormatter = toDateTimeFormatter(input.targetConfig());
        return dateTimeFormatter == null ? value.toString() : dateTimeFormatter.format(value);
    }
}
