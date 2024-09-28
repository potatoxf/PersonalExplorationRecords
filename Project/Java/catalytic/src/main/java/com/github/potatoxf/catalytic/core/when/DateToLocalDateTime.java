 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.ZoneOffset;
 import java.util.Date;

 /**
  * 转换器类型，{@link Date}转换为{@link LocalDateTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class DateToLocalDateTime extends BasicWhenConverterImpl<Date, LocalDateTime> {
     private static volatile DateToLocalDateTime INSTANCE;

     private DateToLocalDateTime() {
     }

     public static DateToLocalDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (DateToLocalDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new DateToLocalDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDateTime apply(Date value, BasicWhenConverterInput input) throws Throwable {
         ZoneOffset originZoneOffset = toZoneOffset(input.originConfig(), ZoneOffset.UTC);
         ZoneOffset targetZoneOffset = toZoneOffset(input.targetConfig(), ZoneOffset.UTC);
         LocalDateTime v = LocalDateTime.ofInstant(value.toInstant(), ZoneOffset.UTC);
         if (!originZoneOffset.equals(targetZoneOffset)) {
             v = v.atOffset(originZoneOffset).withOffsetSameInstant(targetZoneOffset).toLocalDateTime();
         }
         return v;
     }
 }
