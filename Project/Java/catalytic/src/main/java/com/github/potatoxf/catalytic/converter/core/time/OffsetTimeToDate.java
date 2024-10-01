 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.time;

 import com.github.potatoxf.catalytic.converter.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.converter.core.when.OffsetDateTimeToDate;

 import java.time.OffsetTime;
 import java.util.Date;

 /**
  * 转换器类型，{@link OffsetTime}转换为{@link Date}
  *
  * @author potatoxf
  */
 public class OffsetTimeToDate extends BasicTimeConverterImpl<OffsetTime, Date> {
     private static volatile OffsetTimeToDate INSTANCE;

     private OffsetTimeToDate() {
     }

     public static OffsetTimeToDate getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetTimeToDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetTimeToDate();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Date apply(OffsetTime value, BasicTimeConverterInput input) throws Throwable {
         return OffsetDateTimeToDate.getInstance().apply(input.useOrigin(ofOffsetDateTime(value, input)));
     }
 }
