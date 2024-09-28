 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.OffsetDateTime;
 import java.time.ZoneOffset;

 /**
  * 转换器类型，{@link LocalDateTime}转换为{@link OffsetDateTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LocalDateTimeToOffsetDateTime extends BasicWhenConverterImpl<LocalDateTime, OffsetDateTime> {
     private static volatile LocalDateTimeToOffsetDateTime INSTANCE;

     private LocalDateTimeToOffsetDateTime() {
     }

     public static LocalDateTimeToOffsetDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LocalDateTimeToOffsetDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LocalDateTimeToOffsetDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected OffsetDateTime apply(LocalDateTime value, BasicWhenConverterInput input) throws Throwable {
         ZoneOffset originZoneOffset = toZoneOffset(input.originConfig(), ZoneOffset.UTC);
         ZoneOffset targetZoneOffset = toZoneOffset(input.targetConfig(), ZoneOffset.UTC);
         if (originZoneOffset == targetZoneOffset) {
             return OffsetDateTime.of(value, originZoneOffset);
         }
         OffsetDateTime v = value.atOffset(originZoneOffset);
         v = v.withOffsetSameInstant(targetZoneOffset);
         return v;
     }
 }
