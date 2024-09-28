 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.time;

 import com.github.potatoxf.catalytic.core.BasicTimeConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicTimeConverterInput;
 import com.github.potatoxf.catalytic.core.when.LocalDateTimeToInstant;

 import java.time.Instant;
 import java.time.LocalTime;

 /**
  * 转换器类型，{@link LocalTime}转换为{@link Instant}
  *
  * @author potatoxf
  */
 public class LocalTimeToInstant extends BasicTimeConverterImpl<LocalTime, Instant> {
     private static volatile LocalTimeToInstant INSTANCE;

     private LocalTimeToInstant() {
     }

     public static LocalTimeToInstant getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalTimeToInstant.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalTimeToInstant();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Instant apply(LocalTime value, BasicTimeConverterInput input) throws Throwable {
         return LocalDateTimeToInstant.getInstance().apply(input.useOrigin(ofLocalDateTime(value, input)));
     }
 }
