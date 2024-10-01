 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Integer}转换为{@link Float}
  *
  * @author potatoxf
  */
 public class IntegerToFloat extends BasicConverterImpl<Integer, Float> {
     private static volatile IntegerToFloat INSTANCE;

     private IntegerToFloat() {
     }

     public static IntegerToFloat getInstance() {
         if (INSTANCE == null) {
             synchronized (IntegerToFloat.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new IntegerToFloat();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Float apply(Integer value, BasicConverterInput input) throws Throwable {
         return value.floatValue();
     }
 }
