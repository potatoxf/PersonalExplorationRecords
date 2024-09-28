 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Byte}转换为{@link Character}
  *
  * @author potatoxf
  */
 public class ByteToCharacter extends BasicConverterImpl<Byte, Character> {
     private static volatile ByteToCharacter INSTANCE;

     private ByteToCharacter() {
     }

     public static ByteToCharacter getInstance() {
         if (INSTANCE == null) {
             synchronized (ByteToCharacter.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ByteToCharacter();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Character apply(Byte value, BasicConverterInput input) throws Throwable {
         char[] chars = Character.toChars(value);
         return chars.length >= 1 ? chars[0] : null;
     }
 }
