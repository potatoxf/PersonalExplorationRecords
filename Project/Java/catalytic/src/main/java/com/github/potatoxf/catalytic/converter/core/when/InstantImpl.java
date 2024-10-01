/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core.when;

import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterImpl;
import com.github.potatoxf.catalytic.converter.core.BasicWhenConverterInput;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * {@link Instant}基础转换器，先将{@link Instant}转换为{@link LocalDateTime}，再将转换为{@link Target}
 *
 * @author potatoxf
 */
//SUCCESS
abstract class InstantImpl<Target> extends BasicWhenConverterImpl<Instant, Target> {

    @Override
    protected final Target apply(Instant value, BasicWhenConverterInput input) throws Throwable {
        LocalDateTime localDateTime = InstantToLocalDateTime.getInstance().apply(value, input);
        return delegateLocalDateTime(localDateTime, input.copy().copyConfigFromOriginToTarget());
    }

    protected abstract Target delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable;
}
