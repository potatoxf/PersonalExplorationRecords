 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.time;

 import com.github.potatoxf.catalytic.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.core.when.LocalDateTimeToDate;

 import java.time.LocalTime;
 import java.util.Date;

 /**
  * 转换器类型，{@link LocalTime}转换为{@link Date}
  *
  * @author potatoxf
  */
 public class LocalTimeToDate extends BasicTimeConverterImpl<LocalTime, Date> {
     private static volatile LocalTimeToDate INSTANCE;

     private LocalTimeToDate() {
     }

     public static LocalTimeToDate getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalTimeToDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalTimeToDate();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Date apply(LocalTime value, BasicTimeConverterInput input) throws Throwable {
         return LocalDateTimeToDate.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
