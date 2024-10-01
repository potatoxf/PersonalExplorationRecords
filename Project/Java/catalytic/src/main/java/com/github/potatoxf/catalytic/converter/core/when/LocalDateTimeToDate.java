 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.util.Date;

 /**
  * 转换器类型，{@link LocalDateTime}转换为{@link Date}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LocalDateTimeToDate extends BasicWhenConverterImpl<LocalDateTime, Date> {
     private static volatile LocalDateTimeToDate INSTANCE;

     private LocalDateTimeToDate() {
     }

     public static LocalDateTimeToDate getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateTimeToDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateTimeToDate();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Date apply(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return Date.from(LocalDateTimeToInstant.getInstance().apply(value, input));
     }
 }
