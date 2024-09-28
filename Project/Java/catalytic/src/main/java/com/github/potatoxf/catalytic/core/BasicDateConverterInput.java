/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 基础日期转换器的输入参数转换参数
 *
 * @author potatoxf
 */
public class BasicDateConverterInput extends BasicWhenConverterInput {
    private LocalTime localTime = LocalTime.now();

    protected BasicDateConverterInput() {
    }

    public static BasicDateConverterInput of() {
        return new BasicDateConverterInput();
    }

    @Override
    protected BasicDateConverterInput create() {
        return BasicDateConverterInput.of();
    }

    @Override
    public BasicDateConverterInput copy() {
        BasicDateConverterInput other = (BasicDateConverterInput) super.copy();
        if (this != other) {
            other.localTime = localTime;
        }
        return other;
    }

    public LocalTime localTime() {
        return localTime;
    }

    public BasicDateConverterInput useLocalTime(LocalTime localTime) {
        if (localTime != null) {
            this.localTime = localTime;
            return this;
        }
        return this;
    }

    @Override
    public BasicDateConverterInput useOriginFormatter(String originFormatter) {
        super.useOriginFormatter(originFormatter);
        return this;
    }

    @Override
    public BasicDateConverterInput useOriginFormatter(DateTimeFormatter originFormatter) {
        super.useOriginFormatter(originFormatter);
        return this;
    }

    @Override
    public BasicDateConverterInput useOriginFormatter(SimpleDateFormat originFormatter) {
        super.useOriginFormatter(originFormatter);
        return this;
    }

    @Override
    public BasicDateConverterInput useOriginZone(String zone) {
        super.useOriginZone(zone);
        return this;
    }

    @Override
    public BasicDateConverterInput useOriginZone(ZoneId zone) {
        super.useOriginZone(zone);
        return this;
    }

    @Override
    public BasicDateConverterInput useTargetFormatter(String targetFormatter) {
        super.useTargetFormatter(targetFormatter);
        return this;
    }

    @Override
    public BasicDateConverterInput useTargetFormatter(DateTimeFormatter targetFormatter) {
        super.useTargetFormatter(targetFormatter);
        return this;
    }

    @Override
    public BasicDateConverterInput useTargetFormatter(SimpleDateFormat targetFormatter) {
        super.useTargetFormatter(targetFormatter);
        return this;
    }

    @Override
    public BasicDateConverterInput useTargetZone(String zone) {
        super.useTargetZone(zone);
        return this;
    }

    @Override
    public BasicDateConverterInput useTargetZone(ZoneId zone) {
        super.useTargetZone(zone);
        return this;
    }

    @Override
    public BasicDateConverterInput useOrigin(Object origin) {
        super.useOrigin(origin);
        return this;
    }

    @Override
    public BasicDateConverterInput useTarget(Object target) {
        super.useTarget(target);
        return this;
    }

    @Override
    public BasicDateConverterInput useTarget(Class<?> targetType) {
        super.useTarget(targetType);
        return this;
    }
}
