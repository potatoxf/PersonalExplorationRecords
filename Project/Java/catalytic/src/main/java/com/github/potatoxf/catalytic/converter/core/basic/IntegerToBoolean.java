 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Integer}转换为{@link Boolean}
  *
  * @author potatoxf
  */
 public class IntegerToBoolean extends BasicConverterImpl<Integer, Boolean> {
     private static volatile IntegerToBoolean INSTANCE;

     private IntegerToBoolean() {
     }

     public static IntegerToBoolean getInstance() {
         if (INSTANCE == null) {
             synchronized (IntegerToBoolean.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new IntegerToBoolean();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Boolean apply(Integer value, BasicConverterInput input) throws Throwable {
         return value > 0;
     }
 }
