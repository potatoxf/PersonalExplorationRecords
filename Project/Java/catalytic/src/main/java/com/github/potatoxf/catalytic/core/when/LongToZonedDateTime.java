 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link Long}转换为{@link ZonedDateTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LongToZonedDateTime extends LongImpl<ZonedDateTime> {
     private static volatile LongToZonedDateTime INSTANCE;

     private LongToZonedDateTime() {
     }

     public static LongToZonedDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToZonedDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToZonedDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected ZonedDateTime delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToZonedDateTime.getInstance().apply(value, input);
     }
 }
