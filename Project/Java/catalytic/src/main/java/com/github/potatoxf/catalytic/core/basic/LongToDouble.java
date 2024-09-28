 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Long}转换为{@link Double}
  *
  * @author potatoxf
  */
 public class LongToDouble extends BasicConverterImpl<Long, Double> {
     private static volatile LongToDouble INSTANCE;

     private LongToDouble() {
     }

     public static LongToDouble getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToDouble.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToDouble();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Double apply(Long value, BasicConverterInput input) throws Throwable {
         return value.doubleValue();
     }
 }
