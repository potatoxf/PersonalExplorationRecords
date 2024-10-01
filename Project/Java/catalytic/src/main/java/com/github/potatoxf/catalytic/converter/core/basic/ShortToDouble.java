 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Short}转换为{@link Double}
  *
  * @author potatoxf
  */
 public class ShortToDouble extends BasicConverterImpl<Short, Double> {
     private static volatile ShortToDouble INSTANCE;

     private ShortToDouble() {
     }

     public static ShortToDouble getInstance() {
         if (INSTANCE == null) {
             synchronized (ShortToDouble.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ShortToDouble();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Double apply(Short value, BasicConverterInput input) throws Throwable {
         return value.doubleValue();
     }
 }
