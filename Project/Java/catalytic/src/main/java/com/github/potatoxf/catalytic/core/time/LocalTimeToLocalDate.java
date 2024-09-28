 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.time;

 import com.github.potatoxf.catalytic.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.core.when.LocalDateTimeToLocalDate;

 import java.time.LocalDate;
 import java.time.LocalTime;

 /**
  * 转换器类型，{@link LocalTime}转换为{@link LocalDate}
  *
  * @author potatoxf
  */
 public class LocalTimeToLocalDate extends BasicTimeConverterImpl<LocalTime, LocalDate> {
     private static volatile LocalTimeToLocalDate INSTANCE;

     private LocalTimeToLocalDate() {
     }

     public static LocalTimeToLocalDate getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalTimeToLocalDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalTimeToLocalDate();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDate apply(LocalTime value, BasicTimeConverterInput input) throws Throwable {
         return LocalDateTimeToLocalDate.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
