 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Boolean}转换为{@link Character}
  *
  * @author potatoxf
  */
 public class BooleanToCharacter extends BasicConverterImpl<Boolean, Character> {
     private static volatile BooleanToCharacter INSTANCE;

     private BooleanToCharacter() {
     }

     public static BooleanToCharacter getInstance() {
         if (INSTANCE == null) {
             synchronized (BooleanToCharacter.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new BooleanToCharacter();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Character apply(Boolean value, BasicConverterInput input) throws Throwable {
         return value ? 'T' : 'F';
     }
 }
