 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.format.DateTimeFormatter;

 /**
  * 转换器类型，{@link LocalDateTime}转换为{@link String}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LocalDateTimeToString extends BasicWhenConverterImpl<LocalDateTime, String> {
     private static volatile LocalDateTimeToString INSTANCE;

     private LocalDateTimeToString() {
     }

     public static LocalDateTimeToString getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateTimeToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateTimeToString();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected String apply(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         DateTimeFormatter dateTimeFormatter = toDateTimeFormatter(input.targetConfig());
         return dateTimeFormatter == null ? value.toString() : dateTimeFormatter.format(value);
     }
 }
