 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.Instant;
 import java.time.LocalDateTime;
 import java.time.OffsetTime;

 /**
  * 转换器类型，{@link Instant}转换为{@link OffsetTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class InstantToOffsetTime extends InstantImpl<OffsetTime> {
     private static volatile InstantToOffsetTime INSTANCE;

     private InstantToOffsetTime() {
     }

     public static InstantToOffsetTime getInstance() {
         if (INSTANCE == null) {
             synchronized (InstantToOffsetTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new InstantToOffsetTime();
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
