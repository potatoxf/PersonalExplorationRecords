 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.date;

 import com.github.potatoxf.catalytic.core.BasicDateConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicDateConverterInput;
 import com.github.potatoxf.catalytic.core.when.LocalDateTimeToString;

 import java.time.LocalDate;

 /**
  * 转换器类型，{@link LocalDate}转换为{@link String}
  *
  * @author potatoxf
  */
 public class LocalDateToString extends BasicDateConverterImpl<LocalDate, String> {
     private static volatile LocalDateToString INSTANCE;

     private LocalDateToString() {
     }

     public static LocalDateToString getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateToString();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected String apply(LocalDate value, BasicDateConverterInput input) throws Throwable {
         return LocalDateTimeToString.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
