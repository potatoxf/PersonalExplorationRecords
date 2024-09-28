 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetDateTime;
 import java.util.Date;

 /**
  * 转换器类型，{@link Date}转换为{@link OffsetDateTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class DateToOffsetDateTime extends DateImpl<OffsetDateTime> {
     private static volatile DateToOffsetDateTime INSTANCE;

     private DateToOffsetDateTime() {
     }

     public static DateToOffsetDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (DateToOffsetDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DateToOffsetDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected OffsetDateTime delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToOffsetDateTime.getInstance().apply(value, input);
     }
 }
