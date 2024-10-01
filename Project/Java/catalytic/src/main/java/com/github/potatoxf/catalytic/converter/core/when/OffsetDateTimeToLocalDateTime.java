 /*
 * Copyright (c) 2024.
 */
 package com.github.potatoxf.catalytic.converter.core.when;

 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

 import java.time.Instant;
 import java.time.LocalDateTime;
 import java.time.OffsetDateTime;
 import java.time.ZoneOffset;

 /**
  * 转换器类型，{@link OffsetDateTime}转换为{@link LocalDateTime}
  *
  * @author potatoxf
  */
 public class OffsetDateTimeToLocalDateTime extends BasicWhenConverterImpl<OffsetDateTime,LocalDateTime> {
     private static volatile OffsetDateTimeToLocalDateTime INSTANCE;

     private OffsetDateTimeToLocalDateTime() {
     }

     public static OffsetDateTimeToLocalDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (OffsetDateTimeToLocalDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new OffsetDateTimeToLocalDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDateTime apply(OffsetDateTime value, BasicWhenConverterInput input) throws Throwable {
         ZoneOffset originZoneOffset = toZoneOffset(input.originConfig(), ZoneOffset.UTC);
         ZoneOffset targetZoneOffset = toZoneOffset(input.targetConfig(), ZoneOffset.UTC);
         LocalDateTime v = value.atZoneSameInstant(originZoneOffset).toLocalDateTime();
         if (!originZoneOffset.equals(targetZoneOffset)) {
             v = v.atOffset(originZoneOffset).withOffsetSameInstant(targetZoneOffset).toLocalDateTime();
         }
         return v;
     }
 }
