 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.ZonedDateTime;
 import java.util.Date;

 /**
  * 转换器类型，{@link ZonedDateTime}转换为{@link Date}
  *
  * @author potatoxf
  */
 public class ZonedDateTimeToDate extends ZonedDateTimeImpl<Date> {
     private static volatile ZonedDateTimeToDate INSTANCE;

     private ZonedDateTimeToDate() {
     }

     public static ZonedDateTimeToDate getInstance() {
         if (INSTANCE == null) {
             synchronized (ZonedDateTimeToDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ZonedDateTimeToDate();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Date delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return Date.from(LocalDateTimeToInstant.getInstance().apply(value, input));
     }
 }
