 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Short}转换为{@link Integer}
  *
  * @author potatoxf
  */
 public class ShortToInteger extends BasicConverterImpl<Short, Integer> {
     private static volatile ShortToInteger INSTANCE;

     private ShortToInteger() {
     }

     public static ShortToInteger getInstance() {
         if (INSTANCE == null) {
             synchronized (ShortToInteger.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ShortToInteger();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Integer apply(Short value, BasicConverterInput input) throws Throwable {
         return value.intValue();
     }
 }
