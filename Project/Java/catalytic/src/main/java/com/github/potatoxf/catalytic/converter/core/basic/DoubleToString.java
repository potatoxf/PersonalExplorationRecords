 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Double}转换为{@link String}
  *
  * @author potatoxf
  */
 public class DoubleToString extends BasicConverterImpl<Double, String> {
     private static volatile DoubleToString INSTANCE;

     private DoubleToString() {
     }

     public static DoubleToString getInstance() {
         if (INSTANCE == null) {
             synchronized (DoubleToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DoubleToString();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected String apply(Double value, BasicConverterInput input) throws Throwable {
         return value.toString();
     }
 }
