 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Double}转换为{@link Long}
  *
  * @author potatoxf
  */
 public class DoubleToLong extends BasicConverterImpl<Double, Long> {
     private static volatile DoubleToLong INSTANCE;

     private DoubleToLong() {
     }

     public static DoubleToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (DoubleToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DoubleToLong();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Long apply(Double value, BasicConverterInput input) throws Throwable {
         return value.longValue();
     }
 }
