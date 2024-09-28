 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.time;

 import com.github.potatoxf.catalytic.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.core.when.OffsetDateTimeToLocalDate;

 import java.time.LocalDate;
 import java.time.OffsetTime;

 /**
  * 转换器类型，{@link OffsetTime}转换为{@link LocalDate}
  *
  * @author potatoxf
  */
 public class OffsetTimeToLocalDate extends BasicTimeConverterImpl<OffsetTime, LocalDate> {
     private static volatile OffsetTimeToLocalDate INSTANCE;

     private OffsetTimeToLocalDate() {
     }

     public static OffsetTimeToLocalDate getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetTimeToLocalDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetTimeToLocalDate();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDate apply(OffsetTime value, BasicTimeConverterInput input) throws Throwable {
         return OffsetDateTimeToLocalDate.getInstance().apply(input.useOrigin(ofOffsetDateTime(value, input)));
     }
 }
