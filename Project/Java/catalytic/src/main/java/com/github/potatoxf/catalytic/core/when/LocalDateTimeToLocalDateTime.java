 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;

 /**
  * 转换器类型，{@link LocalDateTime}转换为{@link LocalDateTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LocalDateTimeToLocalDateTime extends BasicWhenConverterImpl<LocalDateTime, LocalDateTime> {
     private static volatile LocalDateTimeToLocalDateTime INSTANCE;

     private LocalDateTimeToLocalDateTime() {
     }

     public static LocalDateTimeToLocalDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateTimeToLocalDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateTimeToLocalDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDateTime apply(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToOffsetDateTime.getInstance().apply(value, input).toLocalDateTime();
     }
 }
