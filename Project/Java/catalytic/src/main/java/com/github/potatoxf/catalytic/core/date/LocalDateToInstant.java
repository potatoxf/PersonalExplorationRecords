 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.date;

 import com.github.potatoxf.catalytic.core.BasicDateConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicDateConverterInput;
 import com.github.potatoxf.catalytic.core.when.LocalDateTimeToInstant;

 import java.time.Instant;
 import java.time.LocalDate;

 /**
  * 转换器类型，{@link LocalDate}转换为{@link Instant}
  *
  * @author potatoxf
  */
 public class LocalDateToInstant extends BasicDateConverterImpl<LocalDate, Instant> {
     private static volatile LocalDateToInstant INSTANCE;

     private LocalDateToInstant() {
     }

     public static LocalDateToInstant getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateToInstant.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateToInstant();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Instant apply(LocalDate value, BasicDateConverterInput input) throws Throwable {
         return LocalDateTimeToInstant.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
