/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core.when;

import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

import java.time.LocalDateTime;

/**
 * {@link Long}基础转换器，先将{@link Long}转换为{@link LocalDateTime}，再将转换为{@link Target}
 *
 * @author potatoxf
 */
//SUCCESS
abstract class LongImpl<Target> extends BasicWhenConverterImpl<Long, Target> {

    @Override
    protected final Target apply(Long value, BasicWhenConverterInput input) throws Throwable {
        LocalDateTime localDateTime = LongToLocalDateTime.getInstance().apply(value, input);
        return delegateLocalDateTime(localDateTime, input.copy().copyConfigFromOriginToTarget());
    }

    protected abstract Target delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable;
}
