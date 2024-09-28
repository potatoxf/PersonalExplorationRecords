 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.date;

 import com.github.potatoxf.catalytic.core.BasicDateConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicDateConverterInput;
 import com.github.potatoxf.catalytic.core.when.LocalDateTimeToOffsetDateTime;

 import java.time.LocalDate;
 import java.time.OffsetDateTime;

 /**
  * 转换器类型，{@link LocalDate}转换为{@link OffsetDateTime}
  *
  * @author potatoxf
  */
 public class LocalDateToOffsetDateTime extends BasicDateConverterImpl<LocalDate, OffsetDateTime> {
     private static volatile LocalDateToOffsetDateTime INSTANCE;

     private LocalDateToOffsetDateTime() {
     }

     public static LocalDateToOffsetDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateToOffsetDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateToOffsetDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected OffsetDateTime apply(LocalDate value, BasicDateConverterInput input) throws Throwable {
         return LocalDateTimeToOffsetDateTime.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
