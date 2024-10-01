 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Character}转换为{@link Integer}
  *
  * @author potatoxf
  */
 public class CharacterToInteger extends BasicConverterImpl<Character, Integer> {
     private static volatile CharacterToInteger INSTANCE;

     private CharacterToInteger() {
     }

     public static CharacterToInteger getInstance() {
         if (INSTANCE == null) {
             synchronized (CharacterToInteger.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new CharacterToInteger();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Integer apply(Character value, BasicConverterInput input) throws Throwable {
         return Character.getNumericValue(value);
     }
 }
