 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Byte}转换为{@link String}
  *
  * @author potatoxf
  */
 public class ByteToString extends BasicConverterImpl<Byte, String> {
     private static volatile ByteToString INSTANCE;

     private ByteToString() {
     }

     public static ByteToString getInstance() {
         if (INSTANCE == null) {
             synchronized (ByteToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ByteToString();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected String apply(Byte value, BasicConverterInput input) throws Throwable {
         return value.toString();
     }
 }
