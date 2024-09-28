 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.date;

 import com.github.potatoxf.catalytic.core.BasicDateConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicDateConverterInput;
 import com.github.potatoxf.catalytic.core.when.LocalDateTimeToZonedDateTime;

 import java.time.LocalDate;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link LocalDate}转换为{@link ZonedDateTime}
  *
  * @author potatoxf
  */
 public class LocalDateToZonedDateTime extends BasicDateConverterImpl<LocalDate, ZonedDateTime> {
     private static volatile LocalDateToZonedDateTime INSTANCE;

     private LocalDateToZonedDateTime() {
     }

     public static LocalDateToZonedDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateToZonedDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateToZonedDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected ZonedDateTime apply(LocalDate value, BasicDateConverterInput input) throws Throwable {
         return LocalDateTimeToZonedDateTime.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
