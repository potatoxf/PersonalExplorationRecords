 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Boolean}转换为{@link Double}
  *
  * @author potatoxf
  */
 public class BooleanToDouble extends BasicConverterImpl<Boolean, Double> {
     private static volatile BooleanToDouble INSTANCE;

     private BooleanToDouble() {
     }

     public static BooleanToDouble getInstance() {
         if (INSTANCE == null) {
             synchronized (BooleanToDouble.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new BooleanToDouble();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Double apply(Boolean value, BasicConverterInput input) throws Throwable {
         return value ? 1D : 0D;
     }
 }
