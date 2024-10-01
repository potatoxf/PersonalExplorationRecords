 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.date;


 import com.github.potatoxf.catalytic.converter.core.BasicDateConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicDateConverterInput;
 import com.github.potatoxf.catalytic.converter.core.when.LocalDateTimeToDate;

 import java.time.LocalDate;
 import java.util.Date;

 /**
  * 转换器类型，{@link LocalDate}转换为{@link Date}
  *
  * @author potatoxf
  */
 public class LocalDateToDate extends BasicDateConverterImpl<LocalDate, Date> {
     private static volatile LocalDateToDate INSTANCE;

     private LocalDateToDate() {
     }

     public static LocalDateToDate getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateToDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateToDate();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Date apply(LocalDate value, BasicDateConverterInput input) throws Throwable {
         return LocalDateTimeToDate.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
