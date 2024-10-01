 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Byte}转换为{@link Long}
  *
  * @author potatoxf
  */
 public class ByteToLong extends BasicConverterImpl<Byte, Long> {
     private static volatile ByteToLong INSTANCE;

     private ByteToLong() {
     }

     public static ByteToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (ByteToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ByteToLong();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Long apply(Byte value, BasicConverterInput input) throws Throwable {
         return value.longValue();
     }
 }
