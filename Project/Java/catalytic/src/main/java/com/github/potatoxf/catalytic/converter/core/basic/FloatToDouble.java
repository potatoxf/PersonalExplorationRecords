 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Float}转换为{@link Double}
  *
  * @author potatoxf
  */
 public class FloatToDouble extends BasicConverterImpl<Float, Double> {
     private static volatile FloatToDouble INSTANCE;

     private FloatToDouble() {
     }

     public static FloatToDouble getInstance() {
         if (INSTANCE == null) {
             synchronized (FloatToDouble.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new FloatToDouble();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Double apply(Float value, BasicConverterInput input) throws Throwable {
         return value.doubleValue();
     }
 }
