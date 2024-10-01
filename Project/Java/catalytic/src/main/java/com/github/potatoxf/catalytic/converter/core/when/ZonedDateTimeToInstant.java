 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.Instant;
 import java.time.LocalDateTime;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link ZonedDateTime}转换为{@link Instant}
  *
  * @author potatoxf
  */
 public class ZonedDateTimeToInstant extends ZonedDateTimeImpl<Instant> {
     private static volatile ZonedDateTimeToInstant INSTANCE;

     private ZonedDateTimeToInstant() {
     }

     public static ZonedDateTimeToInstant getInstance() {
         if (INSTANCE == null) {
             synchronized (ZonedDateTimeToInstant.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ZonedDateTimeToInstant();
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
