 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Long}转换为{@link Boolean}
  *
  * @author potatoxf
  */
 public class LongToBoolean extends BasicConverterImpl<Long, Boolean> {
     private static volatile LongToBoolean INSTANCE;

     private LongToBoolean() {
     }

     public static LongToBoolean getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToBoolean.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToBoolean();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Boolean apply(Long value, BasicConverterInput input) throws Throwable {
         return value > 0;
     }
 }
