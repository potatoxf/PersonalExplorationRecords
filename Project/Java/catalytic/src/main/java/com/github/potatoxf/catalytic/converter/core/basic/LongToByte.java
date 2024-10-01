 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Long}转换为{@link Byte}
  *
  * @author potatoxf
  */
 public class LongToByte extends BasicConverterImpl<Long, Byte> {
     private static volatile LongToByte INSTANCE;

     private LongToByte() {
     }

     public static LongToByte getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToByte.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToByte();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Byte apply(Long value, BasicConverterInput input) throws Throwable {
         return value.byteValue();
     }
 }
