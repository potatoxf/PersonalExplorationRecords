 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Double}转换为{@link Integer}
  *
  * @author potatoxf
  */
 public class DoubleToInteger extends BasicConverterImpl<Double, Integer> {
     private static volatile DoubleToInteger INSTANCE;

     private DoubleToInteger() {
     }

     public static DoubleToInteger getInstance() {
         if (INSTANCE == null) {
             synchronized (DoubleToInteger.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DoubleToInteger();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Integer apply(Double value, BasicConverterInput input) throws Throwable {
         return value.intValue();
     }
 }
