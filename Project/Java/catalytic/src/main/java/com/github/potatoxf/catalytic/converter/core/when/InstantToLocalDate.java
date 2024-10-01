 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.Instant;
 import java.time.LocalDate;
 import java.time.LocalDateTime;

 /**
  * 转换器类型，{@link Instant}转换为{@link LocalDate}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class InstantToLocalDate extends InstantImpl<LocalDate> {
     private static volatile InstantToLocalDate INSTANCE;

     private InstantToLocalDate() {
     }

     public static InstantToLocalDate getInstance() {
         if (INSTANCE == null) {
             synchronized (InstantToLocalDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new InstantToLocalDate();
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
