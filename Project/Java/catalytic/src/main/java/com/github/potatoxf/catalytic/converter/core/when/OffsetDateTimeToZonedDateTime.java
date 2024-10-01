 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetDateTime;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link OffsetDateTime}转换为{@link ZonedDateTime}
  *
  * @author potatoxf
  */
 public class OffsetDateTimeToZonedDateTime extends OffsetDateTimeImpl<ZonedDateTime> {
     private static volatile OffsetDateTimeToZonedDateTime INSTANCE;

     private OffsetDateTimeToZonedDateTime() {
     }

     public static OffsetDateTimeToZonedDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetDateTimeToZonedDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetDateTimeToZonedDateTime();
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
