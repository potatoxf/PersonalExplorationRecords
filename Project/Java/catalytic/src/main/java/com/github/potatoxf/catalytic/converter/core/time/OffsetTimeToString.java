 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.time;

 import com.github.potatoxf.catalytic.converter.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.converter.core.when.OffsetDateTimeToString;

 import java.time.OffsetTime;

 /**
  * 转换器类型，{@link OffsetTime}转换为{@link String}
  *
  * @author potatoxf
  */
 public class OffsetTimeToString extends BasicTimeConverterImpl<OffsetTime, String> {
     private static volatile OffsetTimeToString INSTANCE;

     private OffsetTimeToString() {
     }

     public static OffsetTimeToString getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetTimeToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetTimeToString();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected String apply(OffsetTime value, BasicTimeConverterInput input) throws Throwable {
         return OffsetDateTimeToString.getInstance().apply(input.useOrigin(ofOffsetDateTime(value, input)));
     }
 }
