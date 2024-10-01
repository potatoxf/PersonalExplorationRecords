/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic;

/**
 * Base for Exceptions thrown during parsing of a command-line.
 *
 * @author potatoxf
 */
public class ParseException extends CatalyticException {

    public ParseException() {
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }

    public ParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
