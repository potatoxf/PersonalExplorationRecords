 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Byte}转换为{@link Boolean}
  *
  * @author potatoxf
  */
 public class ByteToBoolean extends BasicConverterImpl<Byte, Boolean> {
     private static volatile ByteToBoolean INSTANCE;

     private ByteToBoolean() {
     }

     public static ByteToBoolean getInstance() {
         if (INSTANCE == null) {
             synchronized (ByteToBoolean.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ByteToBoolean();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Boolean apply(Byte value, BasicConverterInput input) throws Throwable {
         return value > 0;
     }
 }
