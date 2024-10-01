 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Byte}转换为{@link Float}
  *
  * @author potatoxf
  */
 public class ByteToFloat extends BasicConverterImpl<Byte, Float> {
     private static volatile ByteToFloat INSTANCE;

     private ByteToFloat() {
     }

     public static ByteToFloat getInstance() {
         if (INSTANCE == null) {
             synchronized (ByteToFloat.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ByteToFloat();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Float apply(Byte value, BasicConverterInput input) throws Throwable {
         return value.floatValue();
     }
 }
