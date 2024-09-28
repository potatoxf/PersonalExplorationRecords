 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Short}转换为{@link String}
  *
  * @author potatoxf
  */
 public class ShortToString extends BasicConverterImpl<Short, String> {
     private static volatile ShortToString INSTANCE;

     private ShortToString() {
     }

     public static ShortToString getInstance() {
         if (INSTANCE == null) {
             synchronized (ShortToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ShortToString();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected String apply(Short value, BasicConverterInput input) throws Throwable {
         return value.toString();
     }
 }
