 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link String}转换为{@link Double}
  *
  * @author potatoxf
  */
 public class StringToDouble extends BasicConverterImpl<String, Double> {
     private static volatile StringToDouble INSTANCE;

     private StringToDouble() {
     }

     public static StringToDouble getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToDouble.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToDouble();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Double apply(String value, BasicConverterInput input) throws Throwable {
         return Double.parseDouble(value);
     }
 }
