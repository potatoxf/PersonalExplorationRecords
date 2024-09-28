 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;

 /**
  * 转换器类型，{@link String}转换为{@link LocalDateTime}
  *
  * @author potatoxf
  */
 public class StringToLocalDateTime extends StringImpl<LocalDateTime> {
     private static volatile StringToLocalDateTime INSTANCE;

     private StringToLocalDateTime() {
     }

     public static StringToLocalDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToLocalDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToLocalDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDateTime delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToLocalDateTime.getInstance().apply(value, input);
     }
 }
