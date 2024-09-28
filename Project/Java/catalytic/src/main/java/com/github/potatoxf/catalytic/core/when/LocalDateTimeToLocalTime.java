 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.LocalTime;

 /**
  * 转换器类型，{@link LocalDateTime}转换为{@link LocalTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LocalDateTimeToLocalTime extends BasicWhenConverterImpl<LocalDateTime, LocalTime> {
     private static volatile LocalDateTimeToLocalTime INSTANCE;

     private LocalDateTimeToLocalTime() {
     }

     public static LocalDateTimeToLocalTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateTimeToLocalTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateTimeToLocalTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalTime apply(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToOffsetDateTime.getInstance().apply(value, input).toLocalTime();
     }
 }
