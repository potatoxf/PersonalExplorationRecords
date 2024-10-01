 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Character}转换为{@link Long}
  *
  * @author potatoxf
  */
 public class CharacterToLong extends BasicConverterImpl<Character, Long> {
     private static volatile CharacterToLong INSTANCE;

     private CharacterToLong() {
     }

     public static CharacterToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (CharacterToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new CharacterToLong();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Long apply(Character value, BasicConverterInput input) throws Throwable {
         return (long) Character.getNumericValue(value);
     }
 }
