 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.time;

 import com.github.potatoxf.catalytic.converter.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.converter.core.when.OffsetDateTimeToLocalTime;

 import java.time.LocalTime;
 import java.time.OffsetTime;

 /**
  * 转换器类型，{@link OffsetTime}转换为{@link LocalTime}
  *
  * @author potatoxf
  */
 public class OffsetTimeToLocalTime extends BasicTimeConverterImpl<OffsetTime, LocalTime> {
     private static volatile OffsetTimeToLocalTime INSTANCE;

     private OffsetTimeToLocalTime() {
     }

     public static OffsetTimeToLocalTime getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetTimeToLocalTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetTimeToLocalTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalTime apply(OffsetTime value, BasicTimeConverterInput input) throws Throwable {
         return OffsetDateTimeToLocalTime.getInstance().apply(input.useOrigin(ofOffsetDateTime(value, input)));
     }
 }
