 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.LocalTime;

 /**
  * 转换器类型，{@link String}转换为{@link LocalTime}
  *
  * @author potatoxf
  */
 public class StringToLocalTime extends StringImpl<LocalTime> {
     private static volatile StringToLocalTime INSTANCE;

     private StringToLocalTime() {
     }

     public static StringToLocalTime getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToLocalTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToLocalTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalTime delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToLocalTime.getInstance().apply(value, input);
     }
 }
