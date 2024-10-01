 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Long}转换为{@link String}
  *
  * @author potatoxf
  */
 public class LongToString extends BasicConverterImpl<Long, String> {
     private static volatile LongToString INSTANCE;

     private LongToString() {
     }

     public static LongToString getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToString();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected String apply(Long value, BasicConverterInput input) throws Throwable {
         return value.toString();
     }
 }
