 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Boolean}转换为{@link Byte}
  *
  * @author potatoxf
  */
 public class BooleanToByte extends BasicConverterImpl<Boolean, Byte> {
     private static volatile BooleanToByte INSTANCE;

     private BooleanToByte() {
     }

     public static BooleanToByte getInstance() {
         if (INSTANCE == null) {
             synchronized (BooleanToByte.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new BooleanToByte();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Byte apply(Boolean value, BasicConverterInput input) throws Throwable {
         return (byte) (value ? 1 : 0);
     }
 }
