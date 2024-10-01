 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link String}转换为{@link ZonedDateTime}
  *
  * @author potatoxf
  */
 public class StringToZonedDateTime extends StringImpl<ZonedDateTime> {
     private static volatile StringToZonedDateTime INSTANCE;

     private StringToZonedDateTime() {
     }

     public static StringToZonedDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToZonedDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToZonedDateTime();
                 }
             }
         }
         return INSTANCE;
     }


     @Override
     protected ZonedDateTime delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToZonedDateTime.getInstance().apply(value, input);
     }
 }
