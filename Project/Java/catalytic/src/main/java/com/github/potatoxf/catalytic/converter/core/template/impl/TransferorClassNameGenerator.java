/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core.template.impl;

import com.github.potatoxf.catalytic.converter.core.template.ClassNameGenerator;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author potatoxf
 */
public class TransferorClassNameGenerator extends ClassNameGenerator<TransferorParameterClass, TransferorParameterObject> {

    @Override
    public boolean isSupport(TransferorParameterClass parameterClass, TransferorParameterObject parameterObject) {
        return true;
    }

    @Override
    public String generate(TransferorParameterClass parameterClass, TransferorParameterObject parameterObject) throws Throwable {
        MessageDigest messageDigest = MessageDigest.getInstance("MD2");
        messageDigest.update(parameterClass.origin().getName().getBytes(StandardCharsets.UTF_8));
        messageDigest.update("To".getBytes(StandardCharsets.UTF_8));
        messageDigest.update(parameterClass.origin().getName().getBytes(StandardCharsets.UTF_8));
        byte[] digest = messageDigest.digest("Finish".getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder(64);
        sb.append("com.github.potatoxf.catalytic.T");
        for (byte b : digest) {
            sb.append(String.format("%x", b).toUpperCase());
        }
        return sb.toString();
    }
}
