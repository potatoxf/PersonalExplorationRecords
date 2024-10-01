 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.LocalTime;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link ZonedDateTime}转换为{@link LocalTime}
  *
  * @author potatoxf
  */
 public class ZonedDateTimeToLocalTime extends ZonedDateTimeImpl<LocalTime> {
     private static volatile ZonedDateTimeToLocalTime INSTANCE;

     private ZonedDateTimeToLocalTime() {
     }

     public static ZonedDateTimeToLocalTime getInstance() {
         if (INSTANCE == null) {
             synchronized (ZonedDateTimeToLocalTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ZonedDateTimeToLocalTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalTime delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToLocalTime.getInstance().apply(value, input);
     }
 }
