 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.Instant;
 import java.time.LocalDateTime;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link Instant}转换为{@link ZonedDateTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class InstantToZonedDateTime extends InstantImpl<ZonedDateTime> {
     private static volatile InstantToZonedDateTime INSTANCE;

     private InstantToZonedDateTime() {
     }

     public static InstantToZonedDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (InstantToZonedDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new InstantToZonedDateTime();
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
