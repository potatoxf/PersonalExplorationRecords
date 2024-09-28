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
  * 转换器类型，{@link Instant}转换为{@link LocalDateTime}
  *
  * @author potatoxf
  */
 //SUCCESS
 public class InstantToLocalDateTime extends BasicWhenConverterImpl<Instant, LocalDateTime> {
     private static volatile InstantToLocalDateTime INSTANCE;

     private InstantToLocalDateTime() {
     }

     public static InstantToLocalDateTime getInstance() {
         if (INSTANCE == null) {
             synchronized (InstantToLocalDateTime.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new InstantToLocalDateTime();
                 }
             }
         }
         return INSTANCE;
     }

     @Override
     protected LocalDateTime apply(Instant value, BasicWhenConverterInput input) throws Throwable {
         ZoneOffset originZoneOffset = toZoneOffset(input.originConfig(), ZoneOffset.UTC);
         ZoneOffset targetZoneOffset = toZoneOffset(input.targetConfig(), ZoneOffset.UTC);
         LocalDateTime v = LocalDateTime.ofInstant(value, ZoneOffset.UTC);
         if (!originZoneOffset.equals(targetZoneOffset)) {
             v = v.atOffset(originZoneOffset).withOffsetSameInstant(targetZoneOffset).toLocalDateTime();
         }
         return v;
     }
 }
