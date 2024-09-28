 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Character}转换为{@link Short}
  *
  * @author potatoxf
  */
 public class CharacterToShort extends BasicConverterImpl<Character, Short> {
     private static volatile CharacterToShort INSTANCE;

     private CharacterToShort() {
     }

     public static CharacterToShort getInstance() {
         if (INSTANCE == null) {
             synchronized (CharacterToShort.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new CharacterToShort();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Short apply(Character value, BasicConverterInput input) throws Throwable {
         return (short) Character.getNumericValue(value);
     }
 }
