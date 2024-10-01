 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Boolean}转换为{@link Integer}
  *
  * @author potatoxf
  */
 public class BooleanToInteger extends BasicConverterImpl<Boolean, Integer> {
     private static volatile BooleanToInteger INSTANCE;

     private BooleanToInteger() {
     }

     public static BooleanToInteger getInstance() {
         if (INSTANCE == null) {
             synchronized (BooleanToInteger.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new BooleanToInteger();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Integer apply(Boolean value, BasicConverterInput input) throws Throwable {
         return value ? 1 : 0;
     }
 }
