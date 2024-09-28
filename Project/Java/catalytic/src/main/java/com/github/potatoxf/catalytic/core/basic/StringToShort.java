 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link String}转换为{@link Short}
  *
  * @author potatoxf
  */
 public class StringToShort extends BasicConverterImpl<String, Short> {
     private static volatile StringToShort INSTANCE;

     private StringToShort() {
     }

     public static StringToShort getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToShort.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToShort();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Short apply(String value, BasicConverterInput input) throws Throwable {
         return Short.parseShort(value);
     }
 }
