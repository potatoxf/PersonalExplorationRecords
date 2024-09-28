 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.Instant;
 import java.time.LocalDateTime;
 import java.util.Date;

 /**
  * 转换器类型，{@link Instant}转换为{@link Date}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class InstantToDate extends InstantImpl<Date> {
     private static volatile InstantToDate INSTANCE;

     private InstantToDate() {
     }

     public static InstantToDate getInstance() {
         if (INSTANCE == null) {
             synchronized (InstantToDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new InstantToDate();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Date delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToDate.getInstance().apply(value, input);
     }
 }
