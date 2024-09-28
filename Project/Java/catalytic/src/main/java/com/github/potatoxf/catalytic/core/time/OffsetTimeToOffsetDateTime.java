 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.time;

 import com.github.potatoxf.catalytic.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.core.when.OffsetDateTimeToOffsetDateTime;

 import java.time.OffsetDateTime;
 import java.time.OffsetTime;

 /**
  * 转换器类型，{@link OffsetTime}转换为{@link OffsetDateTime}
  *
  * @author potatoxf
  */
 public class OffsetTimeToOffsetDateTime extends BasicTimeConverterImpl<OffsetTime, OffsetDateTime> {
     private static volatile OffsetTimeToOffsetDateTime INSTANCE;

     private OffsetTimeToOffsetDateTime() {
     }

     public static OffsetTimeToOffsetDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetTimeToOffsetDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetTimeToOffsetDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected OffsetDateTime apply(OffsetTime value, BasicTimeConverterInput input) throws Throwable {
         return OffsetDateTimeToOffsetDateTime.getInstance().apply(input.useOrigin(ofOffsetDateTime(value, input)));
     }
 }
