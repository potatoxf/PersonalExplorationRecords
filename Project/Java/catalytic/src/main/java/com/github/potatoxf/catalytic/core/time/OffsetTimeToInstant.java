 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.time;

 import com.github.potatoxf.catalytic.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.core.when.OffsetDateTimeToInstant;

 import java.time.Instant;
 import java.time.OffsetTime;

 /**
  * 转换器类型，{@link OffsetTime}转换为{@link Instant}
  *
  * @author potatoxf
  */
 public class OffsetTimeToInstant extends BasicTimeConverterImpl<OffsetTime, Instant> {
     private static volatile OffsetTimeToInstant INSTANCE;

     private OffsetTimeToInstant() {
     }

     public static OffsetTimeToInstant getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetTimeToInstant.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetTimeToInstant();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Instant apply(OffsetTime value, BasicTimeConverterInput input) throws Throwable {
         return OffsetDateTimeToInstant.getInstance().apply(input.useOrigin(ofOffsetDateTime(value, input)));
     }
 }
