 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.Instant;
 import java.time.LocalDateTime;

 /**
  * 转换器类型，{@link Long}转换为{@link Instant}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LongToInstant extends LongImpl<Instant> {
     private static volatile LongToInstant INSTANCE;

     private LongToInstant() {
     }

     public static LongToInstant getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToInstant.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToInstant();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Instant delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToInstant.getInstance().apply(value, input);
     }
 }
