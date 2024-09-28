 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Integer}转换为{@link Character}
  *
  * @author potatoxf
  */
 public class IntegerToCharacter extends BasicConverterImpl<Integer, Character> {
     private static volatile IntegerToCharacter INSTANCE;

     private IntegerToCharacter() {
     }

     public static IntegerToCharacter getInstance() {
         if (INSTANCE == null) {
             synchronized (IntegerToCharacter.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new IntegerToCharacter();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Character apply(Integer value, BasicConverterInput input) throws Throwable {
         char[] chars = Character.toChars(value);
         return chars.length >= 1 ? chars[0] : null;
     }
 }
