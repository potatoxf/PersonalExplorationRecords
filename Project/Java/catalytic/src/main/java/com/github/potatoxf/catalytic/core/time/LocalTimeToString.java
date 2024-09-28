 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.time;

 import com.github.potatoxf.catalytic.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.core.when.LocalDateTimeToString;

 import java.time.LocalTime;

 /**
  * 转换器类型，{@link LocalTime}转换为{@link String}
  *
  * @author potatoxf
  */
 public class LocalTimeToString extends BasicTimeConverterImpl<LocalTime, String> {
     private static volatile LocalTimeToString INSTANCE;

     private LocalTimeToString() {
     }

     public static LocalTimeToString getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalTimeToString.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalTimeToString();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected String apply(LocalTime value, BasicTimeConverterInput input) throws Throwable {
         return LocalDateTimeToString.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
