 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Character}转换为{@link Double}
  *
  * @author potatoxf
  */
 public class CharacterToDouble extends BasicConverterImpl<Character, Double> {
     private static volatile CharacterToDouble INSTANCE;

     private CharacterToDouble() {
     }

     public static CharacterToDouble getInstance() {
         if (INSTANCE == null) {
             synchronized (CharacterToDouble.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new CharacterToDouble();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Double apply(Character value, BasicConverterInput input) throws Throwable {
         return (double) Character.getNumericValue(value);
     }
 }
