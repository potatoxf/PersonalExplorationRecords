 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;

 /**
  * 转换器类型，{@link LocalDateTime}转换为{@link Long}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LocalDateTimeToLong extends BasicWhenConverterImpl<LocalDateTime, Long> {
     private static volatile LocalDateTimeToLong INSTANCE;

     private LocalDateTimeToLong() {
     }

     public static LocalDateTimeToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateTimeToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateTimeToLong();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Long apply(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToInstant.getInstance().apply(value, input).toEpochMilli();
     }
 }
