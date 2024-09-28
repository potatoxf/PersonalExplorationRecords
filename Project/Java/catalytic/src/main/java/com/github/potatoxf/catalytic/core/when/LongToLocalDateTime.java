 /*
  * Copyright (c) 2024.
  */
 package com.github.potatoxf.catalytic.core.when;

 import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
 import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

 import java.time.Instant;
 import java.time.LocalDateTime;
 import java.time.ZoneOffset;

 /**
  * 转换器类型，{@link Long}转换为{@link LocalDateTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class LongToLocalDateTime extends BasicWhenConverterImpl<Long, LocalDateTime> {
     private static volatile LongToLocalDateTime INSTANCE;

     private LongToLocalDateTime() {
     }

     public static LongToLocalDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (LongToLocalDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new LongToLocalDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDateTime apply(Long value, BasicWhenConverterInput input) throws Throwable {
         ZoneOffset originZoneOffset = toZoneOffset(input.originConfig(), ZoneOffset.UTC);
         ZoneOffset targetZoneOffset = toZoneOffset(input.targetConfig(), ZoneOffset.UTC);
         LocalDateTime v = LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneOffset.UTC);
         if (!originZoneOffset.equals(targetZoneOffset)) {
             v = v.atOffset(originZoneOffset).withOffsetSameInstant(targetZoneOffset).toLocalDateTime();
         }
         return v;
     }
 }
