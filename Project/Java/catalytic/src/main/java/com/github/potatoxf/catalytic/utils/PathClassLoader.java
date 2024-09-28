/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils;

import com.github.potatoxf.catalytic.CatalyticException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author potatoxf
 */
public final class PathClassLoader extends ClassLoader {
    /**
     * 指定根路径
     */
    private final Path rootPath;

    public PathClassLoader(Path rootPath) {
        this.rootPath = rootPath;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Path path = rootPath.resolve(name.replace('.', '/') + ".class");
        if (Files.exists(path)) {
            byte[] bytes;
            try {
                bytes = Files.readAllBytes(path);
            } catch (IOException e) {
                throw new ClassNotFoundException("Error read class of '" + name + "'", e);
            }
            return defineClass(name, bytes, 0, bytes.length);
        } else {
            throw new ClassNotFoundException("Not exist class of '" + name + "'");
        }
    }

    public Class<?> writeAndDefineClass(String name, byte[] bytecode) throws CatalyticException {
        Path path = rootPath.resolve(name.replace('.', '/') + ".class");
        try {
            Files.createDirectories(path.getParent());
        } catch (Throwable e) {
            throw new CatalyticException("Error to creating directory [" + path + "]", e);
        }
        Class<?> result;
        // 加载到JVM
        try {
            result = defineClass(name, bytecode, 0, bytecode.length);
        } catch (Throwable e) {
            throw new CatalyticException("Error to define bytecode of [" + name + "]", e);
        }
        // 写入类路径
        try {
            Files.write(path, bytecode);
        } catch (Throwable e) {
            throw new CatalyticException("The corresponding bytecode file could not be generated", e);
        }
        return result;
    }

}
