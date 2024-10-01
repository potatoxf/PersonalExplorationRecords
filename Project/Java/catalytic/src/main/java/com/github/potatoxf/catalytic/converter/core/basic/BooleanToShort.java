 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Boolean}转换为{@link Short}
  *
  * @author potatoxf
  */
 public class BooleanToShort extends BasicConverterImpl<Boolean, Short> {
     private static volatile BooleanToShort INSTANCE;

     private BooleanToShort() {
     }

     public static BooleanToShort getInstance() {
         if (INSTANCE == null) {
             synchronized (BooleanToShort.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new BooleanToShort();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Short apply(Boolean value, BasicConverterInput input) throws Throwable {
         return (short) (value ? 1 : 0);
     }
 }
