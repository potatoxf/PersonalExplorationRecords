 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.Instant;
 import java.time.LocalDateTime;

 /**
  * 转换器类型，{@link String}转换为{@link Instant}
  *
  * @author potatoxf
  */
 public class StringToInstant extends StringImpl<Instant> {
     private static volatile StringToInstant INSTANCE;

     private StringToInstant() {
     }

     public static StringToInstant getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToInstant.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToInstant();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Instant delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToInstant.getInstance().apply(value, input);
     }
 }
