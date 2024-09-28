 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.date;

 import com.github.potatoxf.catalytic.core.BasicDateConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicDateConverterInput;
 import com.github.potatoxf.catalytic.core.when.LocalDateTimeToOffsetTime;

 import java.time.LocalDate;
 import java.time.OffsetTime;

 /**
  * 转换器类型，{@link LocalDate}转换为{@link OffsetTime}
  *
  * @author potatoxf
  */
 public class LocalDateToOffsetTime extends BasicDateConverterImpl<LocalDate, OffsetTime> {
     private static volatile LocalDateToOffsetTime INSTANCE;

     private LocalDateToOffsetTime() {
     }

     public static LocalDateToOffsetTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateToOffsetTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateToOffsetTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected OffsetTime apply(LocalDate value, BasicDateConverterInput input) throws Throwable {
         return LocalDateTimeToOffsetTime.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
