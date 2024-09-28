 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Short}转换为{@link Character}
  *
  * @author potatoxf
  */
 public class ShortToCharacter extends BasicConverterImpl<Short, Character> {
     private static volatile ShortToCharacter INSTANCE;

     private ShortToCharacter() {
     }

     public static ShortToCharacter getInstance() {
         if (INSTANCE == null) {
             synchronized (ShortToCharacter.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ShortToCharacter();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Character apply(Short value, BasicConverterInput input) throws Throwable {
         char[] chars = Character.toChars(value.intValue());
         return chars.length >= 1 ? chars[0] : null;
     }
 }
