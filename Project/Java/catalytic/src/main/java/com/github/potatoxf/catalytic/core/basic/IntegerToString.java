 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Integer}转换为{@link String}
  *
  * @author potatoxf
  */
 public class IntegerToString extends BasicConverterImpl<Integer, String> {
     private static volatile IntegerToString INSTANCE;

     private IntegerToString() {
     }

     public static IntegerToString getInstance() {
         if (INSTANCE == null) {
             synchronized (IntegerToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new IntegerToString();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected String apply(Integer value, BasicConverterInput input) throws Throwable {
         return value.toString();
     }
 }
