 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetDateTime;

 /**
  * 转换器类型，{@link OffsetDateTime}转换为{@link String}
  *
  * @author potatoxf
  */
 public class OffsetDateTimeToString extends OffsetDateTimeImpl<String> {
     private static volatile OffsetDateTimeToString INSTANCE;

     private OffsetDateTimeToString() {
     }

     public static OffsetDateTimeToString getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetDateTimeToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetDateTimeToString();
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
