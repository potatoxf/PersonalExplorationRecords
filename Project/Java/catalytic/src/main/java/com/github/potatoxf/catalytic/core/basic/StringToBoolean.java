 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.basic;

 import com.github.potatoxf.catalytic.core.BasicConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicConverterInput;

 /**
  * 转换器类型，{@link String}转换为{@link Boolean}
  *
  * @author potatoxf
  */
 public class StringToBoolean extends BasicConverterImpl<String, Boolean> {
     private static volatile StringToBoolean INSTANCE;

     private StringToBoolean() {
     }

     public static StringToBoolean getInstance() {
         if (INSTANCE == null) {
             synchronized (StringToBoolean.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new StringToBoolean();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Boolean apply(String value, BasicConverterInput input) throws Throwable {
         if ("true".equalsIgnoreCase(value)) return true;
         if ("false".equalsIgnoreCase(value)) return false;
         if ("T".equalsIgnoreCase(value)) return true;
         if ("F".equalsIgnoreCase(value)) return false;
         if ("对".equalsIgnoreCase(value)) return true;
         if ("错".equalsIgnoreCase(value)) return false;
         if ("√".equalsIgnoreCase(value)) return true;
         if ("×".equalsIgnoreCase(value)) return false;
         if ("ok".equalsIgnoreCase(value)) return true;
         if ("fail".equalsIgnoreCase(value)) return false;
         if ("right".equalsIgnoreCase(value)) return true;
         if ("error".equalsIgnoreCase(value)) return false;
         return null;
     }
 }
