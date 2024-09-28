 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Character}转换为{@link String}
  *
  * @author potatoxf
  */
 public class CharacterToString extends BasicConverterImpl<Character, String> {
     private static volatile CharacterToString INSTANCE;

     private CharacterToString() {
     }

     public static CharacterToString getInstance() {
         if (INSTANCE == null) {
             synchronized (CharacterToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new CharacterToString();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected String apply(Character value, BasicConverterInput input) throws Throwable {
         return value.toString();
     }
 }
