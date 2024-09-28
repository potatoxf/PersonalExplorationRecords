 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDate;
 import java.time.LocalDateTime;
 import java.time.OffsetDateTime;

 /**
  * 转换器类型，{@link OffsetDateTime}转换为{@link LocalDate}
  *
  * @author potatoxf
  */
 public class OffsetDateTimeToLocalDate extends OffsetDateTimeImpl<LocalDate> {
     private static volatile OffsetDateTimeToLocalDate INSTANCE;

     private OffsetDateTimeToLocalDate() {
     }

     public static OffsetDateTimeToLocalDate getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetDateTimeToLocalDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetDateTimeToLocalDate();
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
