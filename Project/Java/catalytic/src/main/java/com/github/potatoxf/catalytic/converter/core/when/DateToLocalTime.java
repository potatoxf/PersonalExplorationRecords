 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.LocalTime;
 import java.util.Date;

 /**
  * 转换器类型，{@link Date}转换为{@link LocalTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class DateToLocalTime extends DateImpl<LocalTime> {
     private static volatile DateToLocalTime INSTANCE;

     private DateToLocalTime() {
     }

     public static DateToLocalTime getInstance() {
         if (INSTANCE == null) {
             synchronized (DateToLocalTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DateToLocalTime();
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
