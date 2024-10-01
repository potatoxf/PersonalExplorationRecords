 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.time;

 import com.github.potatoxf.catalytic.converter.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.converter.core.when.OffsetDateTimeToLocalDateTime;

 import java.time.LocalDateTime;
 import java.time.OffsetTime;

 /**
  * 转换器类型，{@link OffsetTime}转换为{@link LocalDateTime}
  *
  * @author potatoxf
  */
 public class OffsetTimeToLocalDateTime extends BasicTimeConverterImpl<OffsetTime, LocalDateTime> {
     private static volatile OffsetTimeToLocalDateTime INSTANCE;

     private OffsetTimeToLocalDateTime() {
     }

     public static OffsetTimeToLocalDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetTimeToLocalDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetTimeToLocalDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDateTime apply(OffsetTime value, BasicTimeConverterInput input) throws Throwable {
         return OffsetDateTimeToLocalDateTime.getInstance().apply(input.useOrigin(ofOffsetDateTime(value, input)));
     }
 }
