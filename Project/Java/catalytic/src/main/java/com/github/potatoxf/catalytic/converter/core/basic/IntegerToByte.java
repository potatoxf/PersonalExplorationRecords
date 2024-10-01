 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Integer}转换为{@link Byte}
  *
  * @author potatoxf
  */
 public class IntegerToByte extends BasicConverterImpl<Integer, Byte> {
     private static volatile IntegerToByte INSTANCE;

     private IntegerToByte() {
     }

     public static IntegerToByte getInstance() {
         if (INSTANCE == null) {
             synchronized (IntegerToByte.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new IntegerToByte();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Byte apply(Integer value, BasicConverterInput input) throws Throwable {
         return value.byteValue();
     }
 }
