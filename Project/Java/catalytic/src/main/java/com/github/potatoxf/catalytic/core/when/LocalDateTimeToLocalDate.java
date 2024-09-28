 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDate;
 import java.time.LocalDateTime;

 /**
  * 转换器类型，{@link LocalDateTime}转换为{@link LocalDate}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LocalDateTimeToLocalDate extends BasicWhenConverterImpl<LocalDateTime, LocalDate> {
     private static volatile LocalDateTimeToLocalDate INSTANCE;

     private LocalDateTimeToLocalDate() {
     }

     public static LocalDateTimeToLocalDate getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateTimeToLocalDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateTimeToLocalDate();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDate apply(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToOffsetDateTime.getInstance().apply(value, input).toLocalDate();
     }
 }
