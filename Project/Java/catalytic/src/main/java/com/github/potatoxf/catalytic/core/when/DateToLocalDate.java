 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDate;
 import java.time.LocalDateTime;
 import java.util.Date;

 /**
  * 转换器类型，{@link Date}转换为{@link LocalDate}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class DateToLocalDate extends DateImpl<LocalDate> {
     private static volatile DateToLocalDate INSTANCE;

     private DateToLocalDate() {
     }

     public static DateToLocalDate getInstance() {
         if (INSTANCE == null) {
             synchronized (DateToLocalDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DateToLocalDate();
                 }
             }
         }
         return INSTANCE;
     }

     //SUCCESS
     @Override
     protected LocalDate delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToLocalDate.getInstance().apply(value, input);
     }
 }
