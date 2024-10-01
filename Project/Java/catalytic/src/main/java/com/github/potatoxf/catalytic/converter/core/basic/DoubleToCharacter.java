 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Double}转换为{@link Character}
  *
  * @author potatoxf
  */
 public class DoubleToCharacter extends BasicConverterImpl<Double, Character> {
     private static volatile DoubleToCharacter INSTANCE;

     private DoubleToCharacter() {
     }

     public static DoubleToCharacter getInstance() {
         if (INSTANCE == null) {
             synchronized (DoubleToCharacter.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DoubleToCharacter();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Character apply(Double value, BasicConverterInput input) throws Throwable {
         long doubleToLongBits = Double.doubleToLongBits(value);
         int codePoint = Math.toIntExact(doubleToLongBits);
         char[] chars = Character.toChars(codePoint);
         return chars.length >= 1 ? chars[0] : null;
     }
 }
