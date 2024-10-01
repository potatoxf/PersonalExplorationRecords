 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.time;

 import com.github.potatoxf.catalytic.converter.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.converter.core.when.OffsetDateTimeToLong;

 import java.time.OffsetTime;

 /**
  * 转换器类型，{@link OffsetTime}转换为{@link Long}
  *
  * @author potatoxf
  */
 public class OffsetTimeToLong extends BasicTimeConverterImpl<OffsetTime, Long> {
     private static volatile OffsetTimeToLong INSTANCE;

     private OffsetTimeToLong() {
     }

     public static OffsetTimeToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetTimeToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetTimeToLong();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Long apply(OffsetTime value, BasicTimeConverterInput input) throws Throwable {
         return OffsetDateTimeToLong.getInstance().apply(input.useOrigin(ofOffsetDateTime(value, input)));
     }
 }
