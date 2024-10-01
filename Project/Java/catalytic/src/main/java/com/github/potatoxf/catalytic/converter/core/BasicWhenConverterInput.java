/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 基础日期时间转换器的输入参数转换参数
 *
 * @author potatoxf
 */
public class BasicWhenConverterInput extends BasicConverterInput {
    private Object originConfig;
    private Object targetConfig;

    protected BasicWhenConverterInput() {
    }

    public static BasicWhenConverterInput of() {
        return new BasicWhenConverterInput();
    }

    @Override
    protected BasicConverterInput create() {
        return BasicWhenConverterInput.of();
    }

    @Override
    public BasicWhenConverterInput copy() {
        BasicWhenConverterInput other = (BasicWhenConverterInput) super.copy();
        if (this != other) {
            other.originConfig = originConfig;
            other.targetConfig = targetConfig;
        }
        return other;
    }

    public BasicWhenConverterInput copyConfigFromOriginToTarget() {
        targetConfig = originConfig;
        return this;
    }

    public BasicWhenConverterInput copyConfigFromTargetToOrigin() {
        originConfig = targetConfig;
        return this;
    }

    public Object originConfig() {
        return originConfig;
    }

    public Object targetConfig() {
        return targetConfig;
    }

    public BasicWhenConverterInput useOriginFormatter(String originFormatter) {
        if (originFormatter != null) {
            this.originConfig = originFormatter;
        }
        return this;
    }

    public BasicWhenConverterInput useOriginFormatter(DateTimeFormatter originFormatter) {
        if (originFormatter != null) {
            this.originConfig = originFormatter;
        }
        return this;
    }

    public BasicWhenConverterInput useOriginFormatter(SimpleDateFormat originFormatter) {
        if (originFormatter != null) {
            this.originConfig = originFormatter;
        }
        return this;
    }

    public BasicWhenConverterInput useOriginZone(String zone) {
        if (zone != null) {
            this.originConfig = ZoneId.of(zone);
        }
        return this;
    }

    public BasicWhenConverterInput useOriginZone(ZoneId zone) {
        this.originConfig = zone;
        return this;
    }

    public BasicWhenConverterInput useTargetFormatter(String targetFormatter) {
        if (targetFormatter != null) {
            this.targetConfig = targetFormatter;
        }
        return this;
    }

    public BasicWhenConverterInput useTargetFormatter(DateTimeFormatter targetFormatter) {
        if (targetFormatter != null) {
            this.targetConfig = targetFormatter;
        }
        return this;
    }

    public BasicWhenConverterInput useTargetFormatter(SimpleDateFormat targetFormatter) {
        if (targetFormatter != null) {
            this.targetConfig = targetFormatter;
        }
        return this;
    }

    public BasicWhenConverterInput useTargetZone(String zone) {
        if (zone != null) {
            this.targetConfig = ZoneId.of(zone);
        }
        return this;
    }

    public BasicWhenConverterInput useTargetZone(ZoneId zone) {
        this.targetConfig = zone;
        return this;
    }

    @Override
    public BasicWhenConverterInput useOrigin(Object origin) {
        super.useOrigin(origin);
        this.useOriginZone(computeDefaultZoneId(origin));
        return this;
    }

    @Override
    public BasicWhenConverterInput useTarget(Object target) {
        super.useTarget(target);
        this.useOriginZone(computeDefaultZoneId(target));
        return this;
    }

    @Override
    public BasicWhenConverterInput useTarget(Class<?> targetType) {
        super.useTarget(targetType);
        return this;
    }

    protected final ZoneId computeDefaultZoneId(Object value) {
        if (value instanceof Date || value instanceof Instant) {
            return ZoneOffset.UTC;
        } else if (value instanceof ZonedDateTime) {
            return ((ZonedDateTime) value).getZone();
        } else if (value instanceof OffsetDateTime) {
            return ((OffsetDateTime) value).getOffset();
        } else {
            return ZoneId.systemDefault();
        }
    }
}
