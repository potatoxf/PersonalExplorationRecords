 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.time;

 import com.github.potatoxf.catalytic.converter.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.converter.core.when.OffsetDateTimeToZonedDateTime;

 import java.time.OffsetTime;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link OffsetTime}转换为{@link ZonedDateTime}
  *
  * @author potatoxf
  */
 public class OffsetTimeToZonedDateTime extends BasicTimeConverterImpl<OffsetTime, ZonedDateTime> {
     private static volatile OffsetTimeToZonedDateTime INSTANCE;

     private OffsetTimeToZonedDateTime() {
     }

     public static OffsetTimeToZonedDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetTimeToZonedDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetTimeToZonedDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected ZonedDateTime apply(OffsetTime value, BasicTimeConverterInput input) throws Throwable {
         return OffsetDateTimeToZonedDateTime.getInstance().apply(input.useOrigin(ofOffsetDateTime(value, input)));
     }
 }
