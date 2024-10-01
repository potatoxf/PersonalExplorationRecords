 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Character}转换为{@link Byte}
  *
  * @author potatoxf
  */
 public class CharacterToByte extends BasicConverterImpl<Character, Byte> {
     private static volatile CharacterToByte INSTANCE;

     private CharacterToByte() {
     }

     public static CharacterToByte getInstance() {
         if (INSTANCE == null) {
             synchronized (CharacterToByte.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new CharacterToByte();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Byte apply(Character value, BasicConverterInput input) throws Throwable {
         return (byte) Character.getNumericValue(value);
     }
 }
