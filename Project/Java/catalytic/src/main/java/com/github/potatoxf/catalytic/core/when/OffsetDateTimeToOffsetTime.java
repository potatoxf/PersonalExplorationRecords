 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetDateTime;
 import java.time.OffsetTime;

 /**
  * 转换器类型，{@link OffsetDateTime}转换为{@link OffsetTime}
  *
  * @author potatoxf
  */
 public class OffsetDateTimeToOffsetTime extends OffsetDateTimeImpl<OffsetTime> {
     private static volatile OffsetDateTimeToOffsetTime INSTANCE;

     private OffsetDateTimeToOffsetTime() {
     }

     public static OffsetDateTimeToOffsetTime getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetDateTimeToOffsetTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetDateTimeToOffsetTime();
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
