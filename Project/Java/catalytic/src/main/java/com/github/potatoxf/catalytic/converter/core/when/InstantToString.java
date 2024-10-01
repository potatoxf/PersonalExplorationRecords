 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.text.SimpleDateFormat;
 import java.time.Instant;
 import java.time.OffsetDateTime;
 import java.time.ZoneOffset;
 import java.time.format.DateTimeFormatter;
 import java.util.Date;

 /**
  * 转换器类型，{@link Instant}转换为{@link String}
  *
  * @author potatoxf
  */
 public class InstantToString extends BasicWhenConverterImpl<Instant, String> {
     private static volatile InstantToString INSTANCE;

     private InstantToString() {
     }

     public static InstantToString getInstance() {
         if (INSTANCE == null) {
             synchronized (InstantToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new InstantToString();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected String apply(Instant value, BasicWhenConverterInput input) throws Throwable {
         ZoneOffset originZoneOffset = toZoneOffset(input.originConfig(), ZoneOffset.UTC);
         OffsetDateTime originValue = atOffset(value, originZoneOffset);
         DateTimeFormatter dateTimeFormatter = toDateTimeFormatter(input.targetConfig());
         if (dateTimeFormatter != null) {
             return dateTimeFormatter.format(value);
         } else {
             SimpleDateFormat simpleDateFormat = toSimpleDateFormat(input.targetConfig());
             if (simpleDateFormat != null) {
                 return simpleDateFormat.format(Date.from(value));
             } else {
                 return value.toString();
             }
         }
     }
 }
