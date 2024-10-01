 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetTime;

 /**
  * 转换器类型，{@link Long}转换为{@link OffsetTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LongToOffsetTime extends LongImpl<OffsetTime> {
     private static volatile LongToOffsetTime INSTANCE;

     private LongToOffsetTime() {
     }

     public static LongToOffsetTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToOffsetTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToOffsetTime();
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
