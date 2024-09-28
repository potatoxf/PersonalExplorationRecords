 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.LocalTime;

 /**
  * 转换器类型，{@link Long}转换为{@link LocalTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LongToLocalTime extends LongImpl<LocalTime> {
     private static volatile LongToLocalTime INSTANCE;

     private LongToLocalTime() {
     }

     public static LongToLocalTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToLocalTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToLocalTime();
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
