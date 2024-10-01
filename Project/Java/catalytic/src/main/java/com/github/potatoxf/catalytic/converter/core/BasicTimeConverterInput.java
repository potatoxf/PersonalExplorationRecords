/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 基础时间转换器的输入参数转换参数
 *
 * @author potatoxf
 */
public class BasicTimeConverterInput extends BasicWhenConverterInput {
    private LocalDate localDate;

    protected BasicTimeConverterInput() {
    }

    public static BasicTimeConverterInput of() {
        return new BasicTimeConverterInput();
    }

    @Override
    protected BasicTimeConverterInput create() {
        return BasicTimeConverterInput.of();
    }

    @Override
    public BasicTimeConverterInput copy() {
        BasicTimeConverterInput other = (BasicTimeConverterInput) super.copy();
        if (this != other) {
            other.localDate = localDate;
        }
        return other;
    }

    public LocalDate localDate() {
        return localDate;
    }

    public BasicTimeConverterInput useLocalDate(LocalDate localDate) {
        if (localDate != null) {
            this.localDate = localDate;
        }
        return this;
    }

    @Override
    public BasicTimeConverterInput useOriginFormatter(String originFormatter) {
        super.useOriginFormatter(originFormatter);
        return this;
    }

    @Override
    public BasicTimeConverterInput useOriginFormatter(DateTimeFormatter originFormatter) {
        super.useOriginFormatter(originFormatter);
        return this;
    }

    @Override
    public BasicTimeConverterInput useOriginFormatter(SimpleDateFormat originFormatter) {
        super.useOriginFormatter(originFormatter);
        return this;
    }

    @Override
    public BasicTimeConverterInput useOriginZone(String zone) {
        super.useOriginZone(zone);
        return this;
    }

    @Override
    public BasicTimeConverterInput useOriginZone(ZoneId zone) {
        super.useOriginZone(zone);
        return this;
    }

    @Override
    public BasicTimeConverterInput useTargetFormatter(String targetFormatter) {
        super.useTargetFormatter(targetFormatter);
        return this;
    }

    @Override
    public BasicTimeConverterInput useTargetFormatter(DateTimeFormatter targetFormatter) {
        super.useTargetFormatter(targetFormatter);
        return this;
    }

    @Override
    public BasicTimeConverterInput useTargetFormatter(SimpleDateFormat targetFormatter) {
        super.useTargetFormatter(targetFormatter);
        return this;
    }

    @Override
    public BasicTimeConverterInput useTargetZone(String zone) {
        super.useTargetZone(zone);
        return this;
    }

    @Override
    public BasicTimeConverterInput useTargetZone(ZoneId zone) {
        super.useTargetZone(zone);
        return this;
    }

    @Override
    public BasicTimeConverterInput useOrigin(Object origin) {
        super.useOrigin(origin);
        return this;
    }

    @Override
    public BasicTimeConverterInput useTarget(Object target) {
        super.useTarget(target);
        return this;
    }

    @Override
    public BasicTimeConverterInput useTarget(Class<?> targetType) {
        super.useTarget(targetType);
        return this;
    }
}
