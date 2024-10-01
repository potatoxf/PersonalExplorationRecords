/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.cli;

import com.github.potatoxf.catalytic.ParseException;

import java.util.Iterator;
import java.util.List;

/**
 * Thrown when a required option has not been provided.
 */
public class MissingOptionException extends ParseException {
    /** This exception {@code serialVersionUID}. */
    private static final long serialVersionUID = 8161889051578563249L;

    /**
     * Build the exception message from the specified list of options.
     *
     * @param missingOptions the list of missing options and groups
     */
    private static String createMessage(final List<?> missingOptions) {
        final StringBuilder buf = new StringBuilder("Missing required option");
        buf.append(missingOptions.size() == 1 ? "" : "s");
        buf.append(": ");

        final Iterator<?> it = missingOptions.iterator();
        while (it.hasNext()) {
            buf.append(it.next());
            if (it.hasNext()) {
                buf.append(", ");
            }
        }

        return buf.toString();
    }

    /** The list of missing options and groups */
    private List missingOptions;

    /**
     * Constructs a new {@code MissingSelectedException} with the specified list of missing options.
     *
     * @param missingOptions the list of missing options and groups
     * @since 1.2
     */
    public MissingOptionException(final List missingOptions) {
        this(createMessage(missingOptions));
        this.missingOptions = missingOptions;
    }

    /**
     * Constructs a new {@code MissingSelectedException} with the specified detail message.
     *
     * @param message the detail message
     */
    public MissingOptionException(final String message) {
        super(message);
    }

    /**
     * Gets the list of options or option groups missing in the command line parsed.
     *
     * @return the missing options, consisting of String instances for simple options, and OptionGroup instances for
     *         required option groups.
     * @since 1.2
     */
    public List getMissingOptions() {
        return missingOptions;
    }
}
