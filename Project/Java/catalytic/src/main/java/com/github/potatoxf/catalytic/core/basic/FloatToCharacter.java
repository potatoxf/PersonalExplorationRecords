 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Float}转换为{@link Character}
  *
  * @author potatoxf
  */
 public class FloatToCharacter extends BasicConverterImpl<Float, Character> {
     private static volatile FloatToCharacter INSTANCE;

     private FloatToCharacter() {
     }

     public static FloatToCharacter getInstance() {
         if (INSTANCE == null) {
             synchronized (FloatToCharacter.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new FloatToCharacter();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Character apply(Float value, BasicConverterInput input) throws Throwable {
         char[] chars = Character.toChars(Float.floatToIntBits(value));
         return chars.length >= 1 ? chars[0] : null;
     }
 }
