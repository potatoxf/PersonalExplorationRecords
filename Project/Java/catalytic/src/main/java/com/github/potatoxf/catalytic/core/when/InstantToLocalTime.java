 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.Instant;
 import java.time.LocalDateTime;
 import java.time.LocalTime;

 /**
  * 转换器类型，{@link Instant}转换为{@link LocalTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class InstantToLocalTime extends InstantImpl<LocalTime> {
     private static volatile InstantToLocalTime INSTANCE;

     private InstantToLocalTime() {
     }

     public static InstantToLocalTime getInstance() {
         if (INSTANCE == null) {
             synchronized (InstantToLocalTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new InstantToLocalTime();
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
