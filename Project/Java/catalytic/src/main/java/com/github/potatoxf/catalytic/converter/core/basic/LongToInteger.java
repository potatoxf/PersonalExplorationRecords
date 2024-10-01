 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Long}转换为{@link Integer}
  *
  * @author potatoxf
  */
 public class LongToInteger extends BasicConverterImpl<Long, Integer> {
     private static volatile LongToInteger INSTANCE;

     private LongToInteger() {
     }

     public static LongToInteger getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToInteger.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToInteger();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Integer apply(Long value, BasicConverterInput input) throws Throwable {
         return value.intValue();
     }
 }
