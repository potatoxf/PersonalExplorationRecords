 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link Long}转换为{@link Character}
  *
  * @author potatoxf
  */
 public class LongToCharacter extends BasicConverterImpl<Long, Character> {
     private static volatile LongToCharacter INSTANCE;

     private LongToCharacter() {
     }

     public static LongToCharacter getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToCharacter.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToCharacter();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Character apply(Long value, BasicConverterInput input) throws Throwable {
         char[] chars = Character.toChars(Math.toIntExact(value));
         return chars.length >= 1 ? chars[0] : null;
     }
 }
