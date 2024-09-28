 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetDateTime;

 /**
  * 转换器类型，{@link Long}转换为{@link OffsetDateTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LongToOffsetDateTime extends LongImpl<OffsetDateTime> {
     private static volatile LongToOffsetDateTime INSTANCE;

     private LongToOffsetDateTime() {
     }

     public static LongToOffsetDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToOffsetDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToOffsetDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected OffsetDateTime delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToOffsetDateTime.getInstance().apply(value, input);
     }
 }
