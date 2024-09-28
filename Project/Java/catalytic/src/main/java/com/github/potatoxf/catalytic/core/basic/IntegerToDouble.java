 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Integer}转换为{@link Double}
  *
  * @author potatoxf
  */
 public class IntegerToDouble extends BasicConverterImpl<Integer, Double> {
     private static volatile IntegerToDouble INSTANCE;

     private IntegerToDouble() {
     }

     public static IntegerToDouble getInstance() {
         if (INSTANCE == null) {
             synchronized (IntegerToDouble.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new IntegerToDouble();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Double apply(Integer value, BasicConverterInput input) throws Throwable {
         return value.doubleValue();
     }
 }
