 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.text.SimpleDateFormat;
 import java.time.OffsetDateTime;
 import java.time.ZoneOffset;
 import java.time.format.DateTimeFormatter;
 import java.util.Date;

 /**
  * 转换器类型，{@link Date}转换为{@link String}
  *
  * @author potatoxf
  */
 public class DateToString extends BasicWhenConverterImpl<Date, String> {
     private static volatile DateToString INSTANCE;

     private DateToString() {
     }

     public static DateToString getInstance() {
         if (INSTANCE == null) {
             synchronized (DateToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DateToString();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected String apply(Date value, BasicWhenConverterInput input) throws Throwable {
         ZoneOffset originZoneOffset = toZoneOffset(input.originConfig(), ZoneOffset.UTC);
         OffsetDateTime originValue = atOffset(value, originZoneOffset);
         SimpleDateFormat simpleDateFormat = toSimpleDateFormat(input.targetConfig());
         if (simpleDateFormat != null) {
             return simpleDateFormat.format(value);
         } else {
             DateTimeFormatter dateTimeFormatter = toDateTimeFormatter(input.targetConfig());
             if (dateTimeFormatter != null) {
                 return dateTimeFormatter.format(value.toInstant());
             } else {
                 return value.toString();
             }
         }
     }
 }
