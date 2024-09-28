 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.time;

 import com.github.potatoxf.catalytic.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.core.when.LocalDateTimeToLocalDateTime;

 import java.time.LocalDateTime;
 import java.time.LocalTime;

 /**
  * 转换器类型，{@link LocalTime}转换为{@link LocalDateTime}
  *
  * @author potatoxf
  */
 public class LocalTimeToLocalDateTime extends BasicTimeConverterImpl<LocalTime, LocalDateTime> {
     private static volatile LocalTimeToLocalDateTime INSTANCE;

     private LocalTimeToLocalDateTime() {
     }

     public static LocalTimeToLocalDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalTimeToLocalDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalTimeToLocalDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDateTime apply(LocalTime value, BasicTimeConverterInput input) throws Throwable {
         return LocalDateTimeToLocalDateTime.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
