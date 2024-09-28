 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.date;

 import com.github.potatoxf.catalytic.core.BasicDateConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicDateConverterInput;
 import com.github.potatoxf.catalytic.core.when.LocalDateTimeToLocalDateTime;

 import java.time.LocalDate;
 import java.time.LocalDateTime;

 /**
  * 转换器类型，{@link LocalDate}转换为{@link LocalDateTime}
  *
  * @author potatoxf
  */
 public class LocalDateToLocalDateTime extends BasicDateConverterImpl<LocalDate, LocalDateTime> {
     private static volatile LocalDateToLocalDateTime INSTANCE;

     private LocalDateToLocalDateTime() {
     }

     public static LocalDateToLocalDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateToLocalDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateToLocalDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDateTime apply(LocalDate value, BasicDateConverterInput input) throws Throwable {
         return LocalDateTimeToLocalDateTime.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
