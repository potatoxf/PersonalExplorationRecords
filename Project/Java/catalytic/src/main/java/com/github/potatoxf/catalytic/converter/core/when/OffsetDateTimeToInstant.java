 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.Instant;
 import java.time.LocalDateTime;
 import java.time.OffsetDateTime;

 /**
  * 转换器类型，{@link OffsetDateTime}转换为{@link Instant}
  *
  * @author potatoxf
  */
 public class OffsetDateTimeToInstant extends OffsetDateTimeImpl<Instant> {
     private static volatile OffsetDateTimeToInstant INSTANCE;

     private OffsetDateTimeToInstant() {
     }

     public static OffsetDateTimeToInstant getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetDateTimeToInstant.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetDateTimeToInstant();
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
