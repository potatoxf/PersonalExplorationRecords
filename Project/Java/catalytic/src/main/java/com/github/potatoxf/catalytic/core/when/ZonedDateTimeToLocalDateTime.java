 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.LocalDateTime;
 import java.time.ZoneOffset;
 import java.time.ZonedDateTime;

 /**
  * 转换器类型，{@link ZonedDateTime}转换为{@link LocalDateTime}
  *
  * @author potatoxf
  */
 public class ZonedDateTimeToLocalDateTime extends BasicWhenConverterImpl<ZonedDateTime, LocalDateTime> {
     private static volatile ZonedDateTimeToLocalDateTime INSTANCE;

     private ZonedDateTimeToLocalDateTime() {
     }

     public static ZonedDateTimeToLocalDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (ZonedDateTimeToLocalDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new ZonedDateTimeToLocalDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDateTime apply(ZonedDateTime value, BasicWhenConverterInput input) throws Throwable {
         ZoneOffset originZoneOffset = toZoneOffset(input.originConfig(), ZoneOffset.UTC);
         ZoneOffset targetZoneOffset = toZoneOffset(input.targetConfig(), ZoneOffset.UTC);
         LocalDateTime v = value.withZoneSameInstant(originZoneOffset).toLocalDateTime();
         if (!originZoneOffset.equals(targetZoneOffset)) {
             v = v.atOffset(originZoneOffset).withOffsetSameInstant(targetZoneOffset).toLocalDateTime();
         }
         return v;
     }
 }
