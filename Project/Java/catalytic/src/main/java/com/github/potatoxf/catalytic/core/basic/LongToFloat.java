 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Long}转换为{@link Float}
  *
  * @author potatoxf
  */
 public class LongToFloat extends BasicConverterImpl<Long, Float> {
     private static volatile LongToFloat INSTANCE;

     private LongToFloat() {
     }

     public static LongToFloat getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToFloat.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToFloat();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Float apply(Long value, BasicConverterInput input) throws Throwable {
         return value.floatValue();
     }
 }
