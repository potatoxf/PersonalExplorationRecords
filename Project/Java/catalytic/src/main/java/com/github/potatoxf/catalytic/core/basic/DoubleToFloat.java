 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Double}转换为{@link Float}
  *
  * @author potatoxf
  */
 public class DoubleToFloat extends BasicConverterImpl<Double, Float> {
     private static volatile DoubleToFloat INSTANCE;

     private DoubleToFloat() {
     }

     public static DoubleToFloat getInstance() {
         if (INSTANCE == null) {
             synchronized (DoubleToFloat.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DoubleToFloat();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Float apply(Double value, BasicConverterInput input) throws Throwable {
         return value.floatValue();
     }
 }
