 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDate;
 import java.time.LocalDateTime;

 /**
  * 转换器类型，{@link Long}转换为{@link LocalDate}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LongToLocalDate extends LongImpl<LocalDate> {
     private static volatile LongToLocalDate INSTANCE;

     private LongToLocalDate() {
     }

     public static LongToLocalDate getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToLocalDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToLocalDate();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDate delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToLocalDate.getInstance().apply(value, input);
     }
 }
