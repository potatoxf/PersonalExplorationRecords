 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.time;

 import com.github.potatoxf.catalytic.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.core.when.LocalDateTimeToLong;

 import java.time.LocalTime;

 /**
  * 转换器类型，{@link LocalTime}转换为{@link Long}
  *
  * @author potatoxf
  */
 public class LocalTimeToLong extends BasicTimeConverterImpl<LocalTime, Long> {
     private static volatile LocalTimeToLong INSTANCE;

     private LocalTimeToLong() {
     }

     public static LocalTimeToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalTimeToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalTimeToLong();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Long apply(LocalTime value, BasicTimeConverterInput input) throws Throwable {
         return LocalDateTimeToLong.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
