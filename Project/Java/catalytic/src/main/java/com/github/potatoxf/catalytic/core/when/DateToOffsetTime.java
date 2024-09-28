 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetTime;
 import java.util.Date;

 /**
  * 转换器类型，{@link Date}转换为{@link OffsetTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class DateToOffsetTime extends DateImpl<OffsetTime> {
     private static volatile DateToOffsetTime INSTANCE;

     private DateToOffsetTime() {
     }

     public static DateToOffsetTime getInstance() {
         if (INSTANCE == null) {
             synchronized (DateToOffsetTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DateToOffsetTime();
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
