 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Character}转换为{@link Boolean}
  *
  * @author potatoxf
  */
 public class CharacterToBoolean extends BasicConverterImpl<Character, Boolean> {
     private static volatile CharacterToBoolean INSTANCE;

     private CharacterToBoolean() {
     }

     public static CharacterToBoolean getInstance() {
         if (INSTANCE == null) {
             synchronized (CharacterToBoolean.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new CharacterToBoolean();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Boolean apply(Character value, BasicConverterInput input) throws Throwable {
         if (value == 'T' || value == 't' || value == '√' || value == '对') return Boolean.TRUE;
         if (value == 'F' || value == 'f' || value == '×' || value == '错') return Boolean.FALSE;
         return null;
     }
 }
