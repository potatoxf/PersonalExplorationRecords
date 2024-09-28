 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetTime;

 /**
  * 转换器类型，{@link String}转换为{@link OffsetTime}
  *
  * @author potatoxf
  */
 public class StringToOffsetTime extends StringImpl<OffsetTime> {
     private static volatile StringToOffsetTime INSTANCE;

     private StringToOffsetTime() {
     }

     public static StringToOffsetTime getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToOffsetTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToOffsetTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected OffsetTime delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToOffsetTime.getInstance().apply(value, input);
     }
 }
