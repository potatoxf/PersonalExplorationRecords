 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Integer}转换为{@link Long}
  *
  * @author potatoxf
  */
 public class IntegerToLong extends BasicConverterImpl<Integer, Long> {
     private static volatile IntegerToLong INSTANCE;

     private IntegerToLong() {
     }

     public static IntegerToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (IntegerToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new IntegerToLong();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Long apply(Integer value, BasicConverterInput input) throws Throwable {
         return value.longValue();
     }
 }
