 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.util.Date;

 /**
  * 转换器类型，{@link String}转换为{@link Date}
  *
  * @author potatoxf
  */
 public class StringToDate extends StringImpl<Date> {
     private static volatile StringToDate INSTANCE;

     private StringToDate() {
     }

     public static StringToDate getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToDate();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Date delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return Date.from(LocalDateTimeToInstant.getInstance().apply(value, input));
     }
 }
