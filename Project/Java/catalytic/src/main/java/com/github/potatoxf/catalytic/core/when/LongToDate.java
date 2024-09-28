 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.util.Date;

 /**
  * 转换器类型，{@link Long}转换为{@link Date}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LongToDate extends LongImpl<Date> {
     private static volatile LongToDate INSTANCE;

     private LongToDate() {
     }

     public static LongToDate getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToDate.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToDate();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected Date delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         return LocalDateTimeToDate.getInstance().apply(value, input);
     }
 }
