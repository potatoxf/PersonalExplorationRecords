/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;

/**
 * 基础时间转换器
 * @author potatoxf
 */
public abstract class BasicTimeConverterImpl<Origin, Target> extends BasicTimeConverter<Origin, Target, BasicTimeConverterInput> {

    public static LocalDateTime ofLocalDateTime(LocalTime value, BasicTimeConverterInput input) {
        return LocalDateTime.of(input.localDate(), value);
    }

    public static OffsetDateTime ofOffsetDateTime(OffsetTime value, BasicTimeConverterInput input) {
        return OffsetDateTime.of(LocalDateTime.of(input.localDate(), value.toLocalTime()), value.getOffset());
    }
}
