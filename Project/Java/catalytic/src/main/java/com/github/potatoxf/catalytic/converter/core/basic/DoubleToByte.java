 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Double}转换为{@link Byte}
  *
  * @author potatoxf
  */
 public class DoubleToByte extends BasicConverterImpl<Double, Byte> {
     private static volatile DoubleToByte INSTANCE;

     private DoubleToByte() {
     }

     public static DoubleToByte getInstance() {
         if (INSTANCE == null) {
             synchronized (DoubleToByte.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DoubleToByte();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Byte apply(Double value, BasicConverterInput input) throws Throwable {
         return value.byteValue();
     }
 }
