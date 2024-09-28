 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetTime;

 /**
  * 转换器类型，{@link LocalDateTime}转换为{@link OffsetTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LocalDateTimeToOffsetTime extends BasicWhenConverterImpl<LocalDateTime, OffsetTime> {
     private static volatile LocalDateTimeToOffsetTime INSTANCE;

     private LocalDateTimeToOffsetTime() {
     }

     public static LocalDateTimeToOffsetTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateTimeToOffsetTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateTimeToOffsetTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected OffsetTime apply(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToOffsetDateTime.getInstance().apply(value, input).toOffsetTime();
     }
 }
