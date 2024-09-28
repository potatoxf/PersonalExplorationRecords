 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link ZonedDateTime}转换为{@link Long}
  *
  * @author potatoxf
  */
 public class ZonedDateTimeToLong extends ZonedDateTimeImpl<Long> {
     private static volatile ZonedDateTimeToLong INSTANCE;

     private ZonedDateTimeToLong() {
     }

     public static ZonedDateTimeToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (ZonedDateTimeToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ZonedDateTimeToLong();
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
