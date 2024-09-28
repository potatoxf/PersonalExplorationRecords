/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core.when;

import com.github.potatoxf.catalytic.core.BasicWhenConverterImpl;
import com.github.potatoxf.catalytic.core.BasicWhenConverterInput;

import java.time.LocalDateTime;

/**
 * {@link String}基础转换器，先将{@link String}转换为{@link LocalDateTime}，再将转换为{@link Target}
 *
 * @author potatoxf
 */
//SUCCESS
abstract class StringImpl<Target> extends BasicWhenConverterImpl<String, Target> {

    @Override
    protected final Target apply(String value, BasicWhenConverterInput input) throws Throwable {
        LocalDateTime localDateTime = StringToLocalDateTime.getInstance().apply(value, input);
        return delegateLocalDateTime(localDateTime, input.copy().copyConfigFromOriginToTarget());
    }

    protected abstract Target delegateLocalDateTime(LocalDateTime value, BasicWhenConverterInput input) throws Throwable;
}
