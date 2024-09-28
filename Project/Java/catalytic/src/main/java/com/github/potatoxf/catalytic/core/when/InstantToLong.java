 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.Instant;
 import java.time.LocalDateTime;

 /**
  * 转换器类型，{@link Instant}转换为{@link Long}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class InstantToLong extends InstantImpl<Long> {
     private static volatile InstantToLong INSTANCE;

     private InstantToLong() {
     }

     public static InstantToLong getInstance() {
         if (INSTANCE == null) {
             synchronized (InstantToLong.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new InstantToLong();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Long delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToLong.getInstance().apply(value, input);
     }
 }
