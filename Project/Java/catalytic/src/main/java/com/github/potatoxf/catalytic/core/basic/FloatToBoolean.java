 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Float}转换为{@link Boolean}
  *
  * @author potatoxf
  */
 public class FloatToBoolean extends BasicConverterImpl<Float, Boolean> {
     private static volatile FloatToBoolean INSTANCE;

     private FloatToBoolean() {
     }

     public static FloatToBoolean getInstance() {
         if (INSTANCE == null) {
             synchronized (FloatToBoolean.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new FloatToBoolean();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Boolean apply(Float value, BasicConverterInput input) throws Throwable {
         return value != 0;
     }
 }
