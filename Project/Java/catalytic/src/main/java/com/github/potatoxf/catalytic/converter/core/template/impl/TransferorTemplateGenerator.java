/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core.template.impl;

import com.github.potatoxf.catalytic.utils.PathClassLoader;
import com.github.potatoxf.catalytic.CatalyticException;
import com.github.potatoxf.catalytic.converter.core.Transferor;
import com.github.potatoxf.catalytic.converter.core.template.TemplateGenerator;

/**
 * @author potatoxf
 */
public class TransferorTemplateGenerator extends TemplateGenerator<Transferor<Object, Object>, TransferorParameterClass, TransferorParameterObject> {

    private static final Class<?>[] CLASSES = new Class[]{java.util.Map.class, java.util.Collection.class, java.sql.ResultSet.class};

    public TransferorTemplateGenerator(PathClassLoader pathClassLoader) {
        super(pathClassLoader);
    }

    @Override
    public boolean isSupport(TransferorParameterClass parameterClass, TransferorParameterObject parameterObject) {
        if (testInclude(parameterClass.origin(), CLASSES)) return false;
        if (testInclude(parameterClass.target(), CLASSES)) return false;
        if (testInclude(parameterClass.origin(), "javax.servlet.http.HttpServletRequest", "javax.servlet.http.HttpServletResponse")) {
            return false;
        }
        return !testInclude(parameterClass.target(), "javax.servlet.http.HttpServletRequest", "javax.servlet.http.HttpServletResponse");
    }

    public <Origin, Target> Transferor<Origin, Target> loadTemplateInstance(Origin originClass, Target targetClass) throws CatalyticException {
        TransferorParameterObject transferorParameterObject = new TransferorParameterObject(originClass, targetClass);
        Transferor<Object, Object> transferor = this.loadTemplateInstance(transferorParameterObject.toParameterClass(), transferorParameterObject);
        return (Transferor<Origin, Target>) transferor;
    }

    @Override
    protected Object invokeTemplateInstance(Transferor<Object, Object> instance, TransferorParameterClass parameterClass, TransferorParameterObject parameterObject) throws CatalyticException {
        return instance.apply(parameterObject.origin(), parameterObject.target());
    }
}
