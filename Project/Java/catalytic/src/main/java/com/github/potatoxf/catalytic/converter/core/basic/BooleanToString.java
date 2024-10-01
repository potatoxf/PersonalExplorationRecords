 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Boolean}转换为{@link String}
  *
  * @author potatoxf
  */
 public class BooleanToString extends BasicConverterImpl<Boolean, String> {
     private static volatile BooleanToString INSTANCE;

     private BooleanToString() {
     }

     public static BooleanToString getInstance() {
         if (INSTANCE == null) {
             synchronized (BooleanToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new BooleanToString();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected String apply(Boolean value, BasicConverterInput input) throws Throwable {
         return value.toString();
     }
 }
