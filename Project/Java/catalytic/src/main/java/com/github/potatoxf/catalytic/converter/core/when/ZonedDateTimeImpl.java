/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core.when;

import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZonedDateTime;

/**
 * {@link ZonedDateTime}基础转换器，先将{@link ZonedDateTime}转换为{@link LocalDateTime}，再将转换为{@link Target}
 *
 * @author potatoxf
 */
//SUCCESS
abstract class ZonedDateTimeImpl<Target> extends BasicWhenConverterImpl<ZonedDateTime, Target> {

    @Override
    protected final Target apply(ZonedDateTime value, BasicWhenConverterInput input) throws Throwable {
        LocalDateTime localDateTime = ZonedDateTimeToLocalDateTime.getInstance().apply(value, input);
        return delegateLocalDateTime(localDateTime, input.copy().copyConfigFromOriginToTarget());
    }

    protected abstract Target delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable;
}
