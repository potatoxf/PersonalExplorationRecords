 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.util.Date;

 /**
  * 转换器类型，{@link Date}转换为{@link Long}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class DateToLong extends DateImpl<Long> {
     private static volatile DateToLong INSTANCE;

     private DateToLong() {
     }

     public static DateToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (DateToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DateToLong();
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
