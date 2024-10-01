/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.cli;

import java.util.Collection;
import java.util.Iterator;

/**
 * Exception thrown when an option can't be identified from a partial name.
 *
 * @since 1.3
 */
public class AmbiguousOptionException extends UnrecognizedOptionException {
    /**
     * This exception {@code serialVersionUID}.
     */
    private static final long serialVersionUID = 5829816121277947229L;

    /**
     * Build the exception message from the specified list of options.
     *
     * @param option
     * @param matchingOptions
     * @return
     */
    private static String createMessage(final String option, final Collection<String> matchingOptions) {
        final StringBuilder buf = new StringBuilder("Ambiguous option: '");
        buf.append(option);
        buf.append("'  (could be: ");

        final Iterator<String> it = matchingOptions.iterator();
        while (it.hasNext()) {
            buf.append('\'');
            buf.append(it.next());
            buf.append('\'');
            if (it.hasNext()) {
                buf.append(", ");
            }
        }
        buf.append(")");

        return buf.toString();
    }

    /** The list of options matching the partial name specified */
    private final Collection<String> matchingOptions;

    /**
     * Constructs a new AmbiguousOptionException.
     *
     * @param option the partial option name
     * @param matchingOptions the options matching the name
     */
    public AmbiguousOptionException(final String option, final Collection<String> matchingOptions) {
        super(createMessage(option, matchingOptions), option);
        this.matchingOptions = matchingOptions;
    }

    /**
     * Gets the options matching the partial name.
     *
     * @return a collection of options matching the name
     */
    public Collection<String> getMatchingOptions() {
        return matchingOptions;
    }
}
