 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetDateTime;

 /**
  * 转换器类型，{@link OffsetDateTime}转换为{@link OffsetDateTime}
  *
  * @author potatoxf
  */
 public class OffsetDateTimeToOffsetDateTime extends OffsetDateTimeImpl<OffsetDateTime> {
     private static volatile OffsetDateTimeToOffsetDateTime INSTANCE;

     private OffsetDateTimeToOffsetDateTime() {
     }

     public static OffsetDateTimeToOffsetDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetDateTimeToOffsetDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetDateTimeToOffsetDateTime();
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
