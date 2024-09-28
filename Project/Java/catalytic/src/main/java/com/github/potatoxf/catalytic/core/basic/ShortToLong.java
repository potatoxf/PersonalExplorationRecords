 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Short}转换为{@link Long}
  *
  * @author potatoxf
  */
 public class ShortToLong extends BasicConverterImpl<Short, Long> {
     private static volatile ShortToLong INSTANCE;

     private ShortToLong() {
     }

     public static ShortToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (ShortToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ShortToLong();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Long apply(Short value, BasicConverterInput input) throws Throwable {
         return value.longValue();
     }
 }
