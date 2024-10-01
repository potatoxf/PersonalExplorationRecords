 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Short}转换为{@link Boolean}
  *
  * @author potatoxf
  */
 public class ShortToBoolean extends BasicConverterImpl<Short, Boolean> {
     private static volatile ShortToBoolean INSTANCE;

     private ShortToBoolean() {
     }

     public static ShortToBoolean getInstance() {
         if (INSTANCE == null) {
             synchronized (ShortToBoolean.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ShortToBoolean();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Boolean apply(Short value, BasicConverterInput input) throws Throwable {
         return value > 0;
     }
 }
