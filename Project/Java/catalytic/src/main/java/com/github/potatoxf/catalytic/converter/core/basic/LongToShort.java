 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Long}转换为{@link Short}
  *
  * @author potatoxf
  */
 public class LongToShort extends BasicConverterImpl<Long, Short> {
     private static volatile LongToShort INSTANCE;

     private LongToShort() {
     }

     public static LongToShort getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToShort.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToShort();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Short apply(Long value, BasicConverterInput input) throws Throwable {
         return value.shortValue();
     }
 }
