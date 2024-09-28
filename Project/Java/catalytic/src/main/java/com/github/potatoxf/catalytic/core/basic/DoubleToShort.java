 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Double}转换为{@link Short}
  *
  * @author potatoxf
  */
 public class DoubleToShort extends BasicConverterImpl<Double, Short> {
     private static volatile DoubleToShort INSTANCE;

     private DoubleToShort() {
     }

     public static DoubleToShort getInstance() {
         if (INSTANCE == null) {
             synchronized (DoubleToShort.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DoubleToShort();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Short apply(Double value, BasicConverterInput input) throws Throwable {
         return value.shortValue();
     }
 }
