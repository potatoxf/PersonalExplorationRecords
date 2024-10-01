 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Short}转换为{@link Float}
  *
  * @author potatoxf
  */
 public class ShortToFloat extends BasicConverterImpl<Short, Float> {
     private static volatile ShortToFloat INSTANCE;

     private ShortToFloat() {
     }

     public static ShortToFloat getInstance() {
         if (INSTANCE == null) {
             synchronized (ShortToFloat.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ShortToFloat();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Float apply(Short value, BasicConverterInput input) throws Throwable {
         return value.floatValue();
     }
 }
