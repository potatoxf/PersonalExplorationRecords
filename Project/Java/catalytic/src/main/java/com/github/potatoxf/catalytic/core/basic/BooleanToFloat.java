 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Boolean}转换为{@link Float}
  *
  * @author potatoxf
  */
 public class BooleanToFloat extends BasicConverterImpl<Boolean, Float> {
     private static volatile BooleanToFloat INSTANCE;

     private BooleanToFloat() {
     }

     public static BooleanToFloat getInstance() {
         if (INSTANCE == null) {
             synchronized (BooleanToFloat.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new BooleanToFloat();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Float apply(Boolean value, BasicConverterInput input) throws Throwable {
         return value ? 1F : 0F;
     }
 }
