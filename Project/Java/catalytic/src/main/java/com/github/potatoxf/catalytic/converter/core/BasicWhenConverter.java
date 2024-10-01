/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core;


import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.function.Function;

/**
 * 基础日期时间转换器
 *
 * @author potatoxf
 */
public abstract class BasicWhenConverter<Origin, Target, I extends BasicWhenConverterInput> extends BasicConverter<Origin, Target, I> {

    /**
     * 转{@link  SimpleDateFormat}
     */
    public static SimpleDateFormat toSimpleDateFormat(Object object) {
        if (object == null) return null;
        if (object instanceof CharSequence) {
            return new SimpleDateFormat(object.toString());
        } else if (object instanceof SimpleDateFormat) {
            return (SimpleDateFormat) object;
        }
        return null;
    }

    /**
     * 转{@link  DateTimeFormatter}
     */
    public static DateTimeFormatter toDateTimeFormatter(Object object) {
        if (object == null) return null;
        if (object instanceof CharSequence) {
            return DateTimeFormatter.ofPattern(object.toString());
        } else if (object instanceof DateTimeFormatter) {
            return (DateTimeFormatter) object;
        } else if (object instanceof SimpleDateFormat) {
            return DateTimeFormatter.ofPattern(((SimpleDateFormat) object).toPattern());
        }
        return null;
    }

    /**
     * 转{@link  ZoneId}
     */
    public static ZoneId toZoneId(Object object, ZoneOffset defaultValue) {
        if (object == null) return defaultValue;
        if (object instanceof ZoneId) {
            return (ZoneId) object;
        } else if (object instanceof String) {
            return ZoneId.of((String) object);
        }
        return defaultValue;
    }

    /**
     * 转{@link  ZoneOffset}
     */
    public static ZoneOffset toZoneOffset(Object object, ZoneOffset defaultValue) {
        if (object == null) return defaultValue;
        if (object instanceof ZoneOffset) {
            return (ZoneOffset) object;
        } else if (object instanceof String) {
            return ZoneOffset.of((String) object);
        } else if (object instanceof ZoneId) {
            return OffsetDateTime.now((ZoneId) object).getOffset();
        }
        return defaultValue;
    }

    public static LocalDateTime toSameLocalDateTime(long epochSecond, int nanoSecond) {
        return LocalDateTime.ofEpochSecond(epochSecond, nanoSecond, ZoneOffset.UTC);
    }

    public static OffsetDateTime toSameOffsetDateTime(LocalDateTime localDateTime) {
        return OffsetDateTime.of(localDateTime, ZoneOffset.UTC);
    }

    public static OffsetDateTime atOffset(Instant input, ZoneOffset zoneOffset) {
        return BasicWhenConverter.atOffset(input.toEpochMilli(), zoneOffset);
    }

    public static OffsetDateTime atOffset(Date input, ZoneOffset zoneOffset) {
        return BasicWhenConverter.atOffset(input.getTime(), zoneOffset);
    }

    public static OffsetDateTime atOffset(long epochMilli, ZoneOffset zoneOffset) {
        return BasicWhenConverter.atOffset(epochMilli / 1000, (epochMilli % 1000) * 1000000, zoneOffset);
    }

    public static OffsetDateTime atOffset(long epochSecond, long nanoSecond, ZoneOffset zoneOffset) {
        return BasicWhenConverter.atOffset(toSameLocalDateTime(epochSecond, (int) nanoSecond), zoneOffset);
    }

    public static OffsetDateTime atOffset(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        return localDateTime.atOffset(zoneOffset);
    }

    public static OffsetDateTime changeZoneOffsetToSameOffsetDateTime(OffsetDateTime input, ZoneOffset zoneOffset) {
        return BasicWhenConverter.changeZoneOffsetToSame(input, zoneOffset, e -> e);
    }

    public static LocalDateTime changeZoneOffsetToSameLocalDateTime(OffsetDateTime input, ZoneOffset zoneOffset) {
        return BasicWhenConverter.changeZoneOffsetToSame(input, zoneOffset, OffsetDateTime::toLocalDateTime);
    }

    public static Date changeZoneOffsetToSameDate(OffsetDateTime input, ZoneOffset zoneOffset) {
        return BasicWhenConverter.changeZoneOffsetToSame(input, zoneOffset, e -> Date.from(e.toInstant()));
    }

    public static Instant changeZoneOffsetToSameInstant(OffsetDateTime input, ZoneOffset zoneOffset) {
        return BasicWhenConverter.changeZoneOffsetToSame(input, zoneOffset, OffsetDateTime::toInstant);
    }

    private static <T> T changeZoneOffsetToSame(OffsetDateTime input, ZoneOffset targetOffset, Function<OffsetDateTime, T> function) {
        OffsetDateTime offsetDateTime = input.withOffsetSameInstant(targetOffset);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(offsetDateTime.toInstant(), offsetDateTime.getOffset());
        offsetDateTime = BasicWhenConverter.toSameOffsetDateTime(localDateTime);
        return function.apply(offsetDateTime);
    }

}
