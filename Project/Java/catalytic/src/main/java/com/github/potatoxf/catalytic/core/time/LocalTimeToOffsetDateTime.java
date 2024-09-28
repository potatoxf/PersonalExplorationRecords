 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.time;

 import com.github.potatoxf.catalytic.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.core.when.LocalDateTimeToOffsetDateTime;

 import java.time.LocalTime;
 import java.time.OffsetDateTime;

 /**
  * 转换器类型，{@link LocalTime}转换为{@link OffsetDateTime}
  *
  * @author potatoxf
  */
 public class LocalTimeToOffsetDateTime extends BasicTimeConverterImpl<LocalTime, OffsetDateTime> {
     private static volatile LocalTimeToOffsetDateTime INSTANCE;

     private LocalTimeToOffsetDateTime() {
     }

     public static LocalTimeToOffsetDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalTimeToOffsetDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalTimeToOffsetDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected OffsetDateTime apply(LocalTime value, BasicTimeConverterInput input) throws Throwable {
         return LocalDateTimeToOffsetDateTime.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
