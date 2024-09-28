 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Character}转换为{@link Float}
  *
  * @author potatoxf
  */
 public class CharacterToFloat extends BasicConverterImpl<Character, Float> {
     private static volatile CharacterToFloat INSTANCE;

     private CharacterToFloat() {
     }

     public static CharacterToFloat getInstance() {
         if (INSTANCE == null) {
             synchronized (CharacterToFloat.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new CharacterToFloat();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Float apply(Character value, BasicConverterInput input) throws Throwable {
         return (float) Character.getNumericValue(value);
     }
 }
