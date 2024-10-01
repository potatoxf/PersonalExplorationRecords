 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.Instant;
 import java.time.LocalDateTime;
 import java.time.OffsetDateTime;

 /**
  * 转换器类型，{@link Instant}转换为{@link OffsetDateTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class InstantToOffsetDateTime extends InstantImpl<OffsetDateTime> {
     private static volatile InstantToOffsetDateTime INSTANCE;

     private InstantToOffsetDateTime() {
     }

     public static InstantToOffsetDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (InstantToOffsetDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new InstantToOffsetDateTime();
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
