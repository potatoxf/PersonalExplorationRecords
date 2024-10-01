 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.LocalTime;
 import java.time.OffsetDateTime;

 /**
  * 转换器类型，{@link OffsetDateTime}转换为{@link LocalTime}
  *
  * @author potatoxf
  */
 public class OffsetDateTimeToLocalTime extends OffsetDateTimeImpl<LocalTime> {
     private static volatile OffsetDateTimeToLocalTime INSTANCE;

     private OffsetDateTimeToLocalTime() {
     }

     public static OffsetDateTimeToLocalTime getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetDateTimeToLocalTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetDateTimeToLocalTime();
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
