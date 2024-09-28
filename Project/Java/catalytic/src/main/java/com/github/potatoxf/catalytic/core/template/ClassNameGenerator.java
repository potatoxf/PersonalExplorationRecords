/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core.template;

/**
 * @author potatoxf
 */
public abstract class ClassNameGenerator<C extends ParameterClass<C>, O extends ParameterObject<C, O>> extends AbstractGenerator<C, O> {

    /**
     * @param parameterClass
     * @param parameterObject
     * @return
     * @throws Throwable
     */
    public abstract String generate(C parameterClass, O parameterObject) throws Throwable;
}
