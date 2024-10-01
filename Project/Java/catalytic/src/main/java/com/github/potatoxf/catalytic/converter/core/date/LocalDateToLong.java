 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.date;

 import com.github.potatoxf.catalytic.converter.core.BasicDateConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicDateConverterInput;
 import com.github.potatoxf.catalytic.converter.core.when.LocalDateTimeToLong;

 import java.time.LocalDate;

 /**
  * 转换器类型，{@link LocalDate}转换为{@link Long}
  *
  * @author potatoxf
  */
 public class LocalDateToLong extends BasicDateConverterImpl<LocalDate, Long> {
     private static volatile LocalDateToLong INSTANCE;

     private LocalDateToLong() {
     }

     public static LocalDateToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateToLong();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Long apply(LocalDate value, BasicDateConverterInput input) throws Throwable {
         return LocalDateTimeToLong.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
