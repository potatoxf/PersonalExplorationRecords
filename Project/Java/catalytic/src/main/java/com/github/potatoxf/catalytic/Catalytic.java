/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic;

import com.github.potatoxf.catalytic.core.*;
import com.github.potatoxf.catalytic.core.basic.*;
import com.github.potatoxf.catalytic.core.category.HttpServletRequestToTreeMap;
import com.github.potatoxf.catalytic.core.category.MapToTreeMap;
import com.github.potatoxf.catalytic.core.category.ResetSetToTreeMap;
import com.github.potatoxf.catalytic.core.date.*;
import com.github.potatoxf.catalytic.core.time.*;
import com.github.potatoxf.catalytic.core.when.*;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Catalytic {
    private static final Map<ConverterKey, BasicConverter<?, ?, ?>> BASIC = new TreeMap<>(ConverterKey::compareTo);
    private static final Map<ConverterKey, CategoryConverter<?, ?>> CATEGORY = new TreeMap<>(ConverterKey::compareTo);

    static {
        //basic
        Catalytic.register(BooleanToByte.getInstance());
        Catalytic.register(BooleanToCharacter.getInstance());
        Catalytic.register(BooleanToDouble.getInstance());
        Catalytic.register(BooleanToFloat.getInstance());
        Catalytic.register(BooleanToInteger.getInstance());
        Catalytic.register(BooleanToLong.getInstance());
        Catalytic.register(BooleanToShort.getInstance());
        Catalytic.register(BooleanToString.getInstance());
        Catalytic.register(ByteToBoolean.getInstance());
        Catalytic.register(ByteToCharacter.getInstance());
        Catalytic.register(ByteToDouble.getInstance());
        Catalytic.register(ByteToFloat.getInstance());
        Catalytic.register(ByteToInteger.getInstance());
        Catalytic.register(ByteToLong.getInstance());
        Catalytic.register(ByteToShort.getInstance());
        Catalytic.register(ByteToString.getInstance());
        Catalytic.register(CharacterToBoolean.getInstance());
        Catalytic.register(CharacterToByte.getInstance());
        Catalytic.register(CharacterToDouble.getInstance());
        Catalytic.register(CharacterToFloat.getInstance());
        Catalytic.register(CharacterToInteger.getInstance());
        Catalytic.register(CharacterToLong.getInstance());
        Catalytic.register(CharacterToShort.getInstance());
        Catalytic.register(CharacterToString.getInstance());
        Catalytic.register(DoubleToBoolean.getInstance());
        Catalytic.register(DoubleToByte.getInstance());
        Catalytic.register(DoubleToCharacter.getInstance());
        Catalytic.register(DoubleToFloat.getInstance());
        Catalytic.register(DoubleToInteger.getInstance());
        Catalytic.register(DoubleToLong.getInstance());
        Catalytic.register(DoubleToShort.getInstance());
        Catalytic.register(DoubleToString.getInstance());
        Catalytic.register(FloatToBoolean.getInstance());
        Catalytic.register(FloatToByte.getInstance());
        Catalytic.register(FloatToCharacter.getInstance());
        Catalytic.register(FloatToDouble.getInstance());
        Catalytic.register(FloatToInteger.getInstance());
        Catalytic.register(FloatToLong.getInstance());
        Catalytic.register(FloatToShort.getInstance());
        Catalytic.register(FloatToString.getInstance());
        Catalytic.register(IntegerToBoolean.getInstance());
        Catalytic.register(IntegerToByte.getInstance());
        Catalytic.register(IntegerToCharacter.getInstance());
        Catalytic.register(IntegerToDouble.getInstance());
        Catalytic.register(IntegerToFloat.getInstance());
        Catalytic.register(IntegerToLong.getInstance());
        Catalytic.register(IntegerToShort.getInstance());
        Catalytic.register(IntegerToString.getInstance());
        Catalytic.register(LongToBoolean.getInstance());
        Catalytic.register(LongToByte.getInstance());
        Catalytic.register(LongToCharacter.getInstance());
        Catalytic.register(LongToDate.getInstance());
        Catalytic.register(LongToDouble.getInstance());
        Catalytic.register(LongToFloat.getInstance());
        Catalytic.register(LongToInstant.getInstance());
        Catalytic.register(LongToInteger.getInstance());
        Catalytic.register(LongToLocalDate.getInstance());
        Catalytic.register(LongToLocalDateTime.getInstance());
        Catalytic.register(LongToLocalTime.getInstance());
        Catalytic.register(LongToOffsetDateTime.getInstance());
        Catalytic.register(LongToOffsetTime.getInstance());
        Catalytic.register(LongToShort.getInstance());
        Catalytic.register(LongToString.getInstance());
        Catalytic.register(ShortToBoolean.getInstance());
        Catalytic.register(ShortToByte.getInstance());
        Catalytic.register(ShortToCharacter.getInstance());
        Catalytic.register(ShortToDouble.getInstance());
        Catalytic.register(ShortToFloat.getInstance());
        Catalytic.register(ShortToInteger.getInstance());
        Catalytic.register(ShortToLong.getInstance());
        Catalytic.register(ShortToString.getInstance());
        Catalytic.register(StringToBoolean.getInstance());
        Catalytic.register(StringToByte.getInstance());
        Catalytic.register(StringToCharacter.getInstance());
        Catalytic.register(StringToDate.getInstance());
        Catalytic.register(StringToDouble.getInstance());
        Catalytic.register(StringToFloat.getInstance());
        Catalytic.register(StringToInstant.getInstance());
        Catalytic.register(StringToInteger.getInstance());
        Catalytic.register(StringToLocalDate.getInstance());
        Catalytic.register(StringToLocalDateTime.getInstance());
        Catalytic.register(StringToLocalTime.getInstance());
        Catalytic.register(StringToLong.getInstance());
        Catalytic.register(StringToOffsetDateTime.getInstance());
        Catalytic.register(StringToOffsetTime.getInstance());
        Catalytic.register(StringToShort.getInstance());
        Catalytic.register(StringToZonedDateTime.getInstance());
        //when
        register(DateToInstant.getInstance());
        register(DateToLocalDate.getInstance());
        register(DateToLocalDateTime.getInstance());
        register(DateToLocalTime.getInstance());
        register(DateToLong.getInstance());
        register(DateToOffsetDateTime.getInstance());
        register(DateToOffsetTime.getInstance());
        register(DateToString.getInstance());
        register(DateToZonedDateTime.getInstance());
        register(InstantToDate.getInstance());
        register(InstantToLocalDate.getInstance());
        register(InstantToLocalDateTime.getInstance());
        register(InstantToLocalTime.getInstance());
        register(InstantToLong.getInstance());
        register(InstantToOffsetDateTime.getInstance());
        register(InstantToOffsetTime.getInstance());
        register(InstantToString.getInstance());
        register(InstantToZonedDateTime.getInstance());
        register(LocalDateTimeToDate.getInstance());
        register(LocalDateTimeToInstant.getInstance());
        register(LocalDateTimeToLocalDateTime.getInstance());
        register(LocalDateTimeToLocalDate.getInstance());
        register(LocalDateTimeToLocalTime.getInstance());
        register(LocalDateTimeToLong.getInstance());
        register(LocalDateTimeToOffsetDateTime.getInstance());
        register(LocalDateTimeToOffsetTime.getInstance());
        register(LocalDateTimeToString.getInstance());
        register(LocalDateTimeToZonedDateTime.getInstance());
        register(OffsetDateTimeToDate.getInstance());
        register(OffsetDateTimeToInstant.getInstance());
        register(OffsetDateTimeToLocalDate.getInstance());
        register(OffsetDateTimeToLocalDateTime.getInstance());
        register(OffsetDateTimeToLocalTime.getInstance());
        register(OffsetDateTimeToLong.getInstance());
        register(OffsetDateTimeToOffsetDateTime.getInstance());
        register(OffsetDateTimeToOffsetTime.getInstance());
        register(OffsetDateTimeToString.getInstance());
        register(OffsetDateTimeToZonedDateTime.getInstance());
        register(ZonedDateTimeToDate.getInstance());
        register(ZonedDateTimeToInstant.getInstance());
        register(ZonedDateTimeToLocalDate.getInstance());
        register(ZonedDateTimeToLocalDateTime.getInstance());
        register(ZonedDateTimeToLocalTime.getInstance());
        register(ZonedDateTimeToLong.getInstance());
        register(ZonedDateTimeToOffsetDateTime.getInstance());
        register(ZonedDateTimeToOffsetTime.getInstance());
        register(ZonedDateTimeToString.getInstance());
        register(ZonedDateTimeToZonedDateTime.getInstance());
        register(LongToZonedDateTime.getInstance());
        //date
        register(LocalDateToDate.getInstance());
        register(LocalDateToInstant.getInstance());
        register(LocalDateToLocalDateTime.getInstance());
        register(LocalDateToLong.getInstance());
        register(LocalDateToOffsetDateTime.getInstance());
        register(LocalDateToOffsetTime.getInstance());
        register(LocalDateToString.getInstance());
        register(LocalDateToZonedDateTime.getInstance());
        //time
        register(LocalTimeToDate.getInstance());
        register(LocalTimeToInstant.getInstance());
        register(LocalTimeToLocalDate.getInstance());
        register(LocalTimeToLocalDateTime.getInstance());
        register(LocalTimeToLong.getInstance());
        register(LocalTimeToOffsetDateTime.getInstance());
        register(LocalTimeToOffsetTime.getInstance());
        register(LocalTimeToString.getInstance());
        register(LocalTimeToZonedDateTime.getInstance());
        register(OffsetTimeToDate.getInstance());
        register(OffsetTimeToInstant.getInstance());
        register(OffsetTimeToLocalDate.getInstance());
        register(OffsetTimeToLocalDateTime.getInstance());
        register(OffsetTimeToLocalTime.getInstance());
        register(OffsetTimeToLong.getInstance());
        register(OffsetTimeToOffsetDateTime.getInstance());
        register(OffsetTimeToString.getInstance());
        register(OffsetTimeToZonedDateTime.getInstance());


        //catalog
        register(HttpServletRequestToTreeMap.getInstance());
        register(MapToTreeMap.getInstance());
        register(ResetSetToTreeMap.getInstance());
    }

    /**
     * 注册基本转换器
     *
     * @param basicConverter 基本转换器
     */
    public static void register(BasicConverter<?, ?, ?> basicConverter) {
        if (basicConverter != null && basicConverter.isLoadedCorrectlyConverter()) {
            Class<?> originType = basicConverter.originType();
            Class<?> targetType = basicConverter.targetType();
            BASIC.put(new ConverterKey(originType, targetType), basicConverter);
        }
    }

    /**
     * 注册类别转换器
     *
     * @param categoryConverter 类别转换器
     */
    public static void register(CategoryConverter<?, ?> categoryConverter) {
        if (categoryConverter != null && categoryConverter.isLoadedCorrectlyConverter()) {
            Class<?> originType = categoryConverter.originType();
            Class<?> targetType = categoryConverter.targetType();
            CATEGORY.put(new ConverterKey(originType, targetType), categoryConverter);
        }
    }

    /**
     * 寻找基本转换器
     *
     * @param originType 来源Class
     * @param targetType 目标Class
     * @param <O>        来源类型
     * @param <T>        目标类型
     * @return 返回{@code BasicConverter<O, T>}，如果没有返回null
     */
    @SuppressWarnings("unchecked")
    public static <O, T, I extends BasicConverterInput, C extends BasicConverter<O, T, I>> C lookupBasicConverter(Class<O> originType, Class<T> targetType) {
        return (C) BASIC.get(new ConverterKey(originType, targetType));
    }

    /**
     * 寻找类别转换器
     *
     * @param originType 来源Class
     * @param targetType 目标Class
     * @param <T>        目标类型
     * @return 返回{@code CategoryConverter<T>}，如果没有返回null
     */
    @SuppressWarnings({"unchecked", "ReassignedVariable"})
    public static <T, I extends CategoryConverterInput> CategoryConverter<T, I> lookupCategoryConverter(Class<?> originType, Class<T> targetType) {
        CategoryConverter<?, ?> categoryConverter = CATEGORY.get(new ConverterKey(originType, targetType));
        if (categoryConverter == null) {
            Collection<CategoryConverter<?, ?>> categoryConverters = CATEGORY.values();
            for (CategoryConverter<?, ?> e : categoryConverters) {
                if (!e.isLoadedCorrectlyConverter()) continue;
                Class<?> ot = e.originType(), tt = e.targetType();
                if (ot.isAssignableFrom(originType) && (tt == targetType || targetType.isAssignableFrom(tt))) {
                    categoryConverter = e;
                    break;
                }
            }
        }
        return (CategoryConverter<T, I>) categoryConverter;
    }
}
