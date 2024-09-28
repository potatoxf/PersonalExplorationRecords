 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Byte}转换为{@link Short}
  *
  * @author potatoxf
  */
 public class ByteToShort extends BasicConverterImpl<Byte, Short> {
     private static volatile ByteToShort INSTANCE;

     private ByteToShort() {
     }

     public static ByteToShort getInstance() {
         if (INSTANCE == null) {
             synchronized (ByteToShort.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ByteToShort();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Short apply(Byte value, BasicConverterInput input) throws Throwable {
         return value.shortValue();
     }
 }
