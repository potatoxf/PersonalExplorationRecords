 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link ZonedDateTime}转换为{@link String}
  *
  * @author potatoxf
  */
 public class ZonedDateTimeToString extends ZonedDateTimeImpl<String> {
     private static volatile ZonedDateTimeToString INSTANCE;

     private ZonedDateTimeToString() {
     }

     public static ZonedDateTimeToString getInstance() {
         if (INSTANCE == null) {
             synchronized (ZonedDateTimeToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ZonedDateTimeToString();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected String delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToString.getInstance().apply(value, input);
     }
 }
