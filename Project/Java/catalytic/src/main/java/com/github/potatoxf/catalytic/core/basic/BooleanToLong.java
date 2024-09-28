 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Boolean}转换为{@link Long}
  *
  * @author potatoxf
  */
 public class BooleanToLong extends BasicConverterImpl<Boolean, Long> {
     private static volatile BooleanToLong INSTANCE;

     private BooleanToLong() {
     }

     public static BooleanToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (BooleanToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new BooleanToLong();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Long apply(Boolean value, BasicConverterInput input) throws Throwable {
         return value ? 1L : 0L;
     }
 }
