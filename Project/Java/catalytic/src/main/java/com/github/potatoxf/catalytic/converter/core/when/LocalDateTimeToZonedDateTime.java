 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link LocalDateTime}转换为{@link ZonedDateTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LocalDateTimeToZonedDateTime extends BasicWhenConverterImpl<LocalDateTime, ZonedDateTime> {
     private static volatile LocalDateTimeToZonedDateTime INSTANCE;

     private LocalDateTimeToZonedDateTime() {
     }

     public static LocalDateTimeToZonedDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateTimeToZonedDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateTimeToZonedDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected ZonedDateTime apply(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToOffsetDateTime.getInstance().apply(value, input).toZonedDateTime();
     }
 }
