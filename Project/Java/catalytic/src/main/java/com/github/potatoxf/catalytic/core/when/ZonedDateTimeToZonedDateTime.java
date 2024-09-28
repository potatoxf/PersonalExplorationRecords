 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link ZonedDateTime}转换为{@link ZonedDateTime}
  *
  * @author potatoxf
  */
 public class ZonedDateTimeToZonedDateTime extends ZonedDateTimeImpl<ZonedDateTime> {
     private static volatile ZonedDateTimeToZonedDateTime INSTANCE;

     private ZonedDateTimeToZonedDateTime() {
     }

     public static ZonedDateTimeToZonedDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (ZonedDateTimeToZonedDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ZonedDateTimeToZonedDateTime();
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
