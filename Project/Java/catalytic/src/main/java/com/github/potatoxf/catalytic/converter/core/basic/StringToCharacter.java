 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.basic;

 import com.github.potatoxf.catalytic.converter.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicConverterInput;

 /**
  * 转换器类型，{@link String}转换为{@link Character}
  *
  * @author potatoxf
  */
 public class StringToCharacter extends BasicConverterImpl<String, Character> {
     private static volatile StringToCharacter INSTANCE;

     private StringToCharacter() {
     }

     public static StringToCharacter getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToCharacter.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToCharacter();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Character apply(String value, BasicConverterInput input) throws Throwable {
         return !value.isEmpty() ? value.charAt(0) : null;
     }
 }
