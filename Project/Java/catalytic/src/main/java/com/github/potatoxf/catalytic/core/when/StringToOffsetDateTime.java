 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetDateTime;

 /**
  * 转换器类型，{@link String}转换为{@link OffsetDateTime}
  *
  * @author potatoxf
  */
 public class StringToOffsetDateTime extends StringImpl<OffsetDateTime> {
     private static volatile StringToOffsetDateTime INSTANCE;

     private StringToOffsetDateTime() {
     }

     public static StringToOffsetDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToOffsetDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToOffsetDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected OffsetDateTime delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToOffsetDateTime.getInstance().apply(value, input);
     }
 }
