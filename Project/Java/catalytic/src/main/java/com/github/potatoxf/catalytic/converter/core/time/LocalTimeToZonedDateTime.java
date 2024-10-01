 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.time;

 import com.github.potatoxf.catalytic.converter.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.converter.core.when.LocalDateTimeToZonedDateTime;

 import java.time.LocalTime;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link LocalTime}转换为{@link ZonedDateTime}
  *
  * @author potatoxf
  */
 public class LocalTimeToZonedDateTime extends BasicTimeConverterImpl<LocalTime, ZonedDateTime> {
     private static volatile LocalTimeToZonedDateTime INSTANCE;

     private LocalTimeToZonedDateTime() {
     }

     public static LocalTimeToZonedDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalTimeToZonedDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalTimeToZonedDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected ZonedDateTime apply(LocalTime value, BasicTimeConverterInput input) throws Throwable {
         return LocalDateTimeToZonedDateTime.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
