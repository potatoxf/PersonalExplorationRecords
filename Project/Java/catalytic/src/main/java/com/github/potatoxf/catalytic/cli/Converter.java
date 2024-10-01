/*
 * Copyright (c) 2024.
 */
package com.github.potatoxf.catalytic.cli;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The definition of the functional interface to call when doing a conversion. Like {@code Function<String,T>} but can throw an Exception.
 *
 * @param <T> The return type for the function.
 * @param <E> The kind of thrown exception or error.
 * @since 1.7.0
 */
@FunctionalInterface
public interface Converter<T, E extends Throwable> {

    /**
     * The default converter. Does nothing.
     */
    Converter<?, RuntimeException> DEFAULT = s -> s;

    /**
     * Class name converter. Calls {@link Class#forName(String)}.
     */
    Converter<Class<?>, ClassNotFoundException> CLASS = Class::forName;

    /**
     * File name converter. Calls {@link File#File(String)}.
     */
    Converter<File, NullPointerException> FILE = File::new;

    /**
     * Path converter. Calls {@link Paths#get(java.net.URI)}.
     */
    Converter<Path, InvalidPathException> PATH = Paths::get;

    /**
     * Number converter. Converts to a Double if a decimal point ('.') is in the string or a Long otherwise.
     */
    Converter<Number, NumberFormatException> NUMBER = s -> s.indexOf('.') != -1 ? (Number) Double.valueOf(s) : (Number) Long.valueOf(s);

    /**
     * Converts a class name to an instance of the class. Uses the Class converter to find the class and then call the default constructor.
     *
     * @see #CLASS
     */
    Converter<Object, ReflectiveOperationException> OBJECT = s -> CLASS.apply(s).getConstructor().newInstance();

    /**
     * Creates a URL. Calls {@link URL#URL(String)}.
     */
    Converter<URL, MalformedURLException> URL = URL::new;

    /**
     * Converts to a date using the format string Form "EEE MMM dd HH:mm:ss zzz yyyy".
     */
    Converter<Date, java.text.ParseException> DATE = s -> new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(s);

    /**
     * Applies the conversion function to the String argument.
     *
     * @param string the String to convert
     * @return the Object from the conversion.
     * @throws E on error.
     */
    T apply(String string) throws E;
}
