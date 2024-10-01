 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetDateTime;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link ZonedDateTime}转换为{@link OffsetDateTime}
  *
  * @author potatoxf
  */
 public class ZonedDateTimeToOffsetDateTime extends ZonedDateTimeImpl<OffsetDateTime> {
     private static volatile ZonedDateTimeToOffsetDateTime INSTANCE;

     private ZonedDateTimeToOffsetDateTime() {
     }

     public static ZonedDateTimeToOffsetDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (ZonedDateTimeToOffsetDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ZonedDateTimeToOffsetDateTime();
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
