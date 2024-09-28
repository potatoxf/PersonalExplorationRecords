 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Double}转换为{@link Boolean}
  *
  * @author potatoxf
  */
 public class DoubleToBoolean extends BasicConverterImpl<Double, Boolean> {
     private static volatile DoubleToBoolean INSTANCE;

     private DoubleToBoolean() {
     }

     public static DoubleToBoolean getInstance() {
         if (INSTANCE == null) {
             synchronized (DoubleToBoolean.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DoubleToBoolean();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Boolean apply(Double value, BasicConverterInput input) throws Throwable {
         return value > 0;
     }
 }
