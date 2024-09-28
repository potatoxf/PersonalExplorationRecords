 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Byte}转换为{@link Integer}
  *
  * @author potatoxf
  */
 public class ByteToInteger extends BasicConverterImpl<Byte, Integer> {
     private static volatile ByteToInteger INSTANCE;

     private ByteToInteger() {
     }

     public static ByteToInteger getInstance() {
         if (INSTANCE == null) {
             synchronized (ByteToInteger.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ByteToInteger();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Integer apply(Byte value, BasicConverterInput input) throws Throwable {
         return value.intValue();
     }
 }
