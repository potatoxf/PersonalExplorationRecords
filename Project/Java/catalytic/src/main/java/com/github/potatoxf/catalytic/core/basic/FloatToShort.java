 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Float}转换为{@link Short}
  *
  * @author potatoxf
  */
 public class FloatToShort extends BasicConverterImpl<Float, Short> {
     private static volatile FloatToShort INSTANCE;

     private FloatToShort() {
     }

     public static FloatToShort getInstance() {
         if (INSTANCE == null) {
             synchronized (FloatToShort.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new FloatToShort();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Short apply(Float value, BasicConverterInput input) throws Throwable {
         return value.shortValue();
     }
 }
