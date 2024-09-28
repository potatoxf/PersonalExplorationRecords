 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.ZonedDateTime;
 import java.util.Date;

 /**
  * 转换器类型，{@link Date}转换为{@link ZonedDateTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class DateToZonedDateTime extends DateImpl<ZonedDateTime> {
     private static volatile DateToZonedDateTime INSTANCE;

     private DateToZonedDateTime() {
     }

     public static DateToZonedDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (DateToZonedDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DateToZonedDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected ZonedDateTime delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToZonedDateTime.getInstance().apply(value, input);
     }
 }
