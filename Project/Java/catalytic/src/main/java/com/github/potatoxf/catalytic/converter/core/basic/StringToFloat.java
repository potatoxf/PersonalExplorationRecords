 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link String}转换为{@link Float}
  *
  * @author potatoxf
  */
 public class StringToFloat extends BasicConverterImpl<String, Float> {
     private static volatile StringToFloat INSTANCE;

     private StringToFloat() {
     }

     public static StringToFloat getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToFloat.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToFloat();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Float apply(String value, BasicConverterInput input) throws Throwable {
         return Float.parseFloat(value);
     }
 }
