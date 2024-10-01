/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter;

import com.github.potatoxf.catalytic.converter.core.*;
import com.github.potatoxf.catalytic.converter.core.basic.*;
import com.github.potatoxf.catalytic.converter.core.category.HttpServletRequestToTreeMap;
import com.github.potatoxf.catalytic.converter.core.category.MapToTreeMap;
import com.github.potatoxf.catalytic.converter.core.category.ResetSetToTreeMap;
import com.github.potatoxf.catalytic.converter.core.date.*;
import com.github.potatoxf.catalytic.converter.core.time.*;
import com.github.potatoxf.catalytic.converter.core.when.*;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Converter {
    private static final Map<ConverterKey, BasicConverter<?, ?, ?>> BASIC = new TreeMap<>(ConverterKey::compareTo);
    private static final Map<ConverterKey, CategoryConverter<?, ?>> CATEGORY = new TreeMap<>(ConverterKey::compareTo);

    static {
        //basic
        Converter.register(BooleanToByte.getInstance());
        Converter.register(BooleanToCharacter.getInstance());
        Converter.register(BooleanToDouble.getInstance());
        Converter.register(BooleanToFloat.getInstance());
        Converter.register(BooleanToInteger.getInstance());
        Converter.register(BooleanToLong.getInstance());
        Converter.register(BooleanToShort.getInstance());
        Converter.register(BooleanToString.getInstance());
        Converter.register(ByteToBoolean.getInstance());
        Converter.register(ByteToCharacter.getInstance());
        Converter.register(ByteToDouble.getInstance());
        Converter.register(ByteToFloat.getInstance());
        Converter.register(ByteToInteger.getInstance());
        Converter.register(ByteToLong.getInstance());
        Converter.register(ByteToShort.getInstance());
        Converter.register(ByteToString.getInstance());
        Converter.register(CharacterToBoolean.getInstance());
        Converter.register(CharacterToByte.getInstance());
        Converter.register(CharacterToDouble.getInstance());
        Converter.register(CharacterToFloat.getInstance());
        Converter.register(CharacterToInteger.getInstance());
        Converter.register(CharacterToLong.getInstance());
        Converter.register(CharacterToShort.getInstance());
        Converter.register(CharacterToString.getInstance());
        Converter.register(DoubleToBoolean.getInstance());
        Converter.register(DoubleToByte.getInstance());
        Converter.register(DoubleToCharacter.getInstance());
        Converter.register(DoubleToFloat.getInstance());
        Converter.register(DoubleToInteger.getInstance());
        Converter.register(DoubleToLong.getInstance());
        Converter.register(DoubleToShort.getInstance());
        Converter.register(DoubleToString.getInstance());
        Converter.register(FloatToBoolean.getInstance());
        Converter.register(FloatToByte.getInstance());
        Converter.register(FloatToCharacter.getInstance());
        Converter.register(FloatToDouble.getInstance());
        Converter.register(FloatToInteger.getInstance());
        Converter.register(FloatToLong.getInstance());
        Converter.register(FloatToShort.getInstance());
        Converter.register(FloatToString.getInstance());
        Converter.register(IntegerToBoolean.getInstance());
        Converter.register(IntegerToByte.getInstance());
        Converter.register(IntegerToCharacter.getInstance());
        Converter.register(IntegerToDouble.getInstance());
        Converter.register(IntegerToFloat.getInstance());
        Converter.register(IntegerToLong.getInstance());
        Converter.register(IntegerToShort.getInstance());
        Converter.register(IntegerToString.getInstance());
        Converter.register(LongToBoolean.getInstance());
        Converter.register(LongToByte.getInstance());
        Converter.register(LongToCharacter.getInstance());
        Converter.register(LongToDate.getInstance());
        Converter.register(LongToDouble.getInstance());
        Converter.register(LongToFloat.getInstance());
        Converter.register(LongToInstant.getInstance());
        Converter.register(LongToInteger.getInstance());
        Converter.register(LongToLocalDate.getInstance());
        Converter.register(LongToLocalDateTime.getInstance());
        Converter.register(LongToLocalTime.getInstance());
        Converter.register(LongToOffsetDateTime.getInstance());
        Converter.register(LongToOffsetTime.getInstance());
        Converter.register(LongToShort.getInstance());
        Converter.register(LongToString.getInstance());
        Converter.register(ShortToBoolean.getInstance());
        Converter.register(ShortToByte.getInstance());
        Converter.register(ShortToCharacter.getInstance());
        Converter.register(ShortToDouble.getInstance());
        Converter.register(ShortToFloat.getInstance());
        Converter.register(ShortToInteger.getInstance());
        Converter.register(ShortToLong.getInstance());
        Converter.register(ShortToString.getInstance());
        Converter.register(StringToBoolean.getInstance());
        Converter.register(StringToByte.getInstance());
        Converter.register(StringToCharacter.getInstance());
        Converter.register(StringToDate.getInstance());
        Converter.register(StringToDouble.getInstance());
        Converter.register(StringToFloat.getInstance());
        Converter.register(StringToInstant.getInstance());
        Converter.register(StringToInteger.getInstance());
        Converter.register(StringToLocalDate.getInstance());
        Converter.register(StringToLocalDateTime.getInstance());
        Converter.register(StringToLocalTime.getInstance());
        Converter.register(StringToLong.getInstance());
        Converter.register(StringToOffsetDateTime.getInstance());
        Converter.register(StringToOffsetTime.getInstance());
        Converter.register(StringToShort.getInstance());
        Converter.register(StringToZonedDateTime.getInstance());
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
