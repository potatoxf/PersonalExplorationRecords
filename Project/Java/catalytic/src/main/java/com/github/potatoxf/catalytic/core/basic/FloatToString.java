 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Float}转换为{@link String}
  *
  * @author potatoxf
  */
 public class FloatToString extends BasicConverterImpl<Float, String> {
     private static volatile FloatToString INSTANCE;

     private FloatToString() {
     }

     public static FloatToString getInstance() {
         if (INSTANCE == null) {
             synchronized (FloatToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new FloatToString();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected String apply(Float value, BasicConverterInput input) throws Throwable {
         return value.toString();
     }
 }
