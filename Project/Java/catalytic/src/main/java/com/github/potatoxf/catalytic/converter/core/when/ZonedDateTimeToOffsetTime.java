 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetTime;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link ZonedDateTime}转换为{@link OffsetTime}
  *
  * @author potatoxf
  */
 public class ZonedDateTimeToOffsetTime extends ZonedDateTimeImpl<OffsetTime> {
     private static volatile ZonedDateTimeToOffsetTime INSTANCE;

     private ZonedDateTimeToOffsetTime() {
     }

     public static ZonedDateTimeToOffsetTime getInstance() {
         if (INSTANCE == null) {
             synchronized (ZonedDateTimeToOffsetTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ZonedDateTimeToOffsetTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected OffsetTime delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToOffsetTime.getInstance().apply(value, input);
     }
 }
