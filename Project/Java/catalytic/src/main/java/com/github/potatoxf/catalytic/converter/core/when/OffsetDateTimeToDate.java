 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetDateTime;
 import java.util.Date;

 /**
  * 转换器类型，{@link OffsetDateTime}转换为{@link Date}
  *
  * @author potatoxf
  */
 public class OffsetDateTimeToDate extends OffsetDateTimeImpl<Date> {
     private static volatile OffsetDateTimeToDate INSTANCE;

     private OffsetDateTimeToDate() {
     }

     public static OffsetDateTimeToDate getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetDateTimeToDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetDateTimeToDate();
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
