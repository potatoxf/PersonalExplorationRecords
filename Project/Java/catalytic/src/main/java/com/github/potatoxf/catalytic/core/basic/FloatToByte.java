 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Float}转换为{@link Byte}
  *
  * @author potatoxf
  */
 public class FloatToByte extends BasicConverterImpl<Float, Byte> {
     private static volatile FloatToByte INSTANCE;

     private FloatToByte() {
     }

     public static FloatToByte getInstance() {
         if (INSTANCE == null) {
             synchronized (FloatToByte.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new FloatToByte();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Byte apply(Float value, BasicConverterInput input) throws Throwable {
         return value.byteValue();
     }
 }
