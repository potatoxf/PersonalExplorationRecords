 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDate;
 import java.time.LocalDateTime;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link ZonedDateTime}转换为{@link LocalDate}
  *
  * @author potatoxf
  */
 public class ZonedDateTimeToLocalDate extends ZonedDateTimeImpl<LocalDate> {
     private static volatile ZonedDateTimeToLocalDate INSTANCE;

     private ZonedDateTimeToLocalDate() {
     }

     public static ZonedDateTimeToLocalDate getInstance() {
         if (INSTANCE == null) {
             synchronized (ZonedDateTimeToLocalDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ZonedDateTimeToLocalDate();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDate delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToLocalDate.getInstance().apply(value, input);
     }
 }
