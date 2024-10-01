/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.cli;

import com.github.potatoxf.catalytic.ParseException;
import com.github.potatoxf.catalytic.cli.Option;

/**
 * Thrown when an option requiring an argument is not provided with an argument.
 */
public class MissingArgumentException extends ParseException {
    /**
     * This exception {@code serialVersionUID}.
     */
    private static final long serialVersionUID = -7098538588704965017L;

    /** The option requiring additional arguments */
    private Option option;

    /**
     * Constructs a new {@code MissingArgumentException} with the specified detail message.
     *
     * @param option the option requiring an argument
     * @since 1.2
     */
    public MissingArgumentException(final Option option) {
        this("Missing argument for option: " + option.getKey());
        this.option = option;
    }

    /**
     * Constructs a new {@code MissingArgumentException} with the specified detail message.
     *
     * @param message the detail message
     */
    public MissingArgumentException(final String message) {
        super(message);
    }

    /**
     * Gets the option requiring an argument that wasn't provided on the command line.
     *
     * @return the related option
     * @since 1.2
     */
    public Option getOption() {
        return option;
    }
}
