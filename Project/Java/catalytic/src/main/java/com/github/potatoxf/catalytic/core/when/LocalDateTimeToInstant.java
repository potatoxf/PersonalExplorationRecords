 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.Instant;
 import java.time.LocalDateTime;

 /**
  * 转换器类型，{@link LocalDateTime}转换为{@link Instant}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LocalDateTimeToInstant extends BasicWhenConverterImpl<LocalDateTime, Instant> {
     private static volatile LocalDateTimeToInstant INSTANCE;

     private LocalDateTimeToInstant() {
     }

     public static LocalDateTimeToInstant getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateTimeToInstant.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateTimeToInstant();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Instant apply(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToOffsetDateTime.getInstance().apply(value, input).toInstant();
     }
 }
