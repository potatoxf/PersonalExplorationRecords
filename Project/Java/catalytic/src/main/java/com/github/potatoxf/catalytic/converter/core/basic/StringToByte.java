 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link String}转换为{@link Byte}
  *
  * @author potatoxf
  */
 public class StringToByte extends BasicConverterImpl<String, Byte> {
     private static volatile StringToByte INSTANCE;

     private StringToByte() {
     }

     public static StringToByte getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToByte.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToByte();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Byte apply(String value, BasicConverterInput input) throws Throwable {
         return Byte.parseByte(value);
     }
 }
