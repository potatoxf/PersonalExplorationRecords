 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Integer}转换为{@link Short}
  *
  * @author potatoxf
  */
 public class IntegerToShort extends BasicConverterImpl<Integer, Short> {
     private static volatile IntegerToShort INSTANCE;

     private IntegerToShort() {
     }

     public static IntegerToShort getInstance() {
         if (INSTANCE == null) {
             synchronized (IntegerToShort.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new IntegerToShort();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Short apply(Integer value, BasicConverterInput input) throws Throwable {
         return value.shortValue();
     }
 }
