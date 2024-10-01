 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Float}转换为{@link Long}
  *
  * @author potatoxf
  */
 public class FloatToLong extends BasicConverterImpl<Float, Long> {
     private static volatile FloatToLong INSTANCE;

     private FloatToLong() {
     }

     public static FloatToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (FloatToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new FloatToLong();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Long apply(Float value, BasicConverterInput input) throws Throwable {
         return value.longValue();
     }
 }
