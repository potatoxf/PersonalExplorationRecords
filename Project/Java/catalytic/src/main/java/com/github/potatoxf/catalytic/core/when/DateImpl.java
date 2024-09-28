/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core.when;

import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * {@link Date}基础转换器，先将{@link Date}转换为{@link LocalDateTime}，再将转换为{@link Target}
 *
 * @author potatoxf
 */
//SUCCESS
abstract class DateImpl<Target> extends BasicWhenConverterImpl<Date, Target> {

    @Override
    protected final Target apply(Date value, BasicWhenConverterInput input) throws Throwable {
        LocalDateTime localDateTime = DateToLocalDateTime.getInstance().apply(value, input);
        return delegateLocalDateTime(localDateTime, input.copy().copyConfigFromOriginToTarget());
    }

    protected abstract Target delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable;
}
