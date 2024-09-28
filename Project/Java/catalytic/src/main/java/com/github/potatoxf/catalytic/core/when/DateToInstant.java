 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.Instant;
 import java.time.LocalDateTime;
 import java.util.Date;

 /**
  * 转换器类型，{@link Date}转换为{@link Instant}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class DateToInstant extends DateImpl<Instant> {
     private static volatile DateToInstant INSTANCE;

     private DateToInstant() {
     }

     public static DateToInstant getInstance() {
         if (INSTANCE == null) {
             synchronized (DateToInstant.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DateToInstant();
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
