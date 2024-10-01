/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 基础日期转换器
 *
 * @author potatoxf
 */
public abstract class BasicDateConverterImpl<Origin, Target> extends BasicDateConverter<Origin, Target, BasicDateConverterInput> {

    public static LocalDateTime ofLocalDateTime(LocalDate value, BasicDateConverterInput input) {
        return LocalDateTime.of(value, input.localTime());
    }
}
