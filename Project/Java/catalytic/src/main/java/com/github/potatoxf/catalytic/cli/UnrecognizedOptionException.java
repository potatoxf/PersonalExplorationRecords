/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.cli;

import com.github.potatoxf.catalytic.ParseException;

/**
 * Thrown during parsing signaling an unrecognized option.
 */
public class UnrecognizedOptionException extends ParseException {

    /**
     * This exception {@code serialVersionUID}.
     */
    private static final long serialVersionUID = -252504690284625623L;

    /** The unrecognized option. */
    private final String option;

    /**
     * Constructs a new {@code UnrecognizedArgumentException} with the specified detail message.
     *
     * @param message the detail message
     */
    public UnrecognizedOptionException(final String message) {
        this(message, null);
    }

    /**
     * Constructs a new {@code UnrecognizedArgumentException} with the specified option and detail message.
     *
     * @param message the detail message
     * @param option the unrecognized option
     * @since 1.2
     */
    public UnrecognizedOptionException(final String message, final String option) {
        super(message);
        this.option = option;
    }

    /**
     * Gets the unrecognized option.
     *
     * @return the related option
     * @since 1.2
     */
    public String getOption() {
        return option;
    }
}
