 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetDateTime;

 /**
  * 转换器类型，{@link OffsetDateTime}转换为{@link Long}
  *
  * @author potatoxf
  */
 public class OffsetDateTimeToLong extends OffsetDateTimeImpl<Long> {
     private static volatile OffsetDateTimeToLong INSTANCE;

     private OffsetDateTimeToLong() {
     }

     public static OffsetDateTimeToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetDateTimeToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetDateTimeToLong();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Long delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToLong.getInstance().apply(value, input);
     }
 }
