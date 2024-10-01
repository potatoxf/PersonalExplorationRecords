 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Byte}转换为{@link Double}
  *
  * @author potatoxf
  */
 public class ByteToDouble extends BasicConverterImpl<Byte, Double> {
     private static volatile ByteToDouble INSTANCE;

     private ByteToDouble() {
     }

     public static ByteToDouble getInstance() {
         if (INSTANCE == null) {
             synchronized (ByteToDouble.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ByteToDouble();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Double apply(Byte value, BasicConverterInput input) throws Throwable {
         return value.doubleValue();
     }
 }
