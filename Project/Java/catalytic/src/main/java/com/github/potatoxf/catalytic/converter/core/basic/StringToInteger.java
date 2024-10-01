 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link String}转换为{@link Integer}
  *
  * @author potatoxf
  */
 public class StringToInteger extends BasicConverterImpl<String, Integer> {
     private static volatile StringToInteger INSTANCE;

     private StringToInteger() {
     }

     public static StringToInteger getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToInteger.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToInteger();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Integer apply(String value, BasicConverterInput input) throws Throwable {
         return Integer.parseInt(value);
     }
 }
