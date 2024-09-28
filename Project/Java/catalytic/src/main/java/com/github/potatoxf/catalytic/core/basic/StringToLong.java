 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link String}转换为{@link Long}
  *
  * @author potatoxf
  */
 public class StringToLong extends BasicConverterImpl<String, Long> {
     private static volatile StringToLong INSTANCE;

     private StringToLong() {
     }

     public static StringToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToLong();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Long apply(String value, BasicConverterInput input) throws Throwable {
         return Long.parseLong(value);
     }
 }
