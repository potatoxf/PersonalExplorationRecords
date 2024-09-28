 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.time;

 import com.github.potatoxf.catalytic.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.core.when.LocalDateTimeToOffsetTime;

 import java.time.LocalTime;
 import java.time.OffsetTime;

 /**
  * 转换器类型，{@link LocalTime}转换为{@link OffsetTime}
  *
  * @author potatoxf
  */
 public class LocalTimeToOffsetTime extends BasicTimeConverterImpl<LocalTime, OffsetTime> {
     private static volatile LocalTimeToOffsetTime INSTANCE;

     private LocalTimeToOffsetTime() {
     }

     public static LocalTimeToOffsetTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalTimeToOffsetTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalTimeToOffsetTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected OffsetTime apply(LocalTime value, BasicTimeConverterInput input) throws Throwable {
         return LocalDateTimeToOffsetTime.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
