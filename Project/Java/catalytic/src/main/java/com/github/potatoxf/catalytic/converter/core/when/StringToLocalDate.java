 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDate;
 import java.time.LocalDateTime;

 /**
  * 转换器类型，{@link String}转换为{@link LocalDate}
  *
  * @author potatoxf
  */
 public class StringToLocalDate extends StringImpl<LocalDate> {
     private static volatile StringToLocalDate INSTANCE;

     private StringToLocalDate() {
     }

     public static StringToLocalDate getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToLocalDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToLocalDate();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDate delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToLocalDate.getInstance().apply(value, input);
     }
 }
