 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Short}转换为{@link Byte}
  *
  * @author potatoxf
  */
 public class ShortToByte extends BasicConverterImpl<Short, Byte> {
     private static volatile ShortToByte INSTANCE;

     private ShortToByte() {
     }

     public static ShortToByte getInstance() {
         if (INSTANCE == null) {
             synchronized (ShortToByte.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ShortToByte();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Byte apply(Short value, BasicConverterInput input) throws Throwable {
         return value.byteValue();
     }
 }
