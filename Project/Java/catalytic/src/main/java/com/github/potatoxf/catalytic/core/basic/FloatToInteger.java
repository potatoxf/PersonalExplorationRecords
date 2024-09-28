 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Float}转换为{@link Integer}
  *
  * @author potatoxf
  */
 public class FloatToInteger extends BasicConverterImpl<Float, Integer> {
     private static volatile FloatToInteger INSTANCE;

     private FloatToInteger() {
     }

     public static FloatToInteger getInstance() {
         if (INSTANCE == null) {
             synchronized (FloatToInteger.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new FloatToInteger();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Integer apply(Float value, BasicConverterInput input) throws Throwable {
         return value.intValue();
     }
 }
