/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core.template;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author potatoxf
 */
public abstract class ClassBytecodeGenerator<C extends ParameterClass<C>, O extends ParameterObject<C, O>> extends AbstractGenerator<C, O> {

    public static String formatClassPathDescriptor(String input) {
        return ClassBytecodeGenerator.formatClassPathDescriptor(input, false);
    }

    public static String formatClassPathDescriptor(String input, boolean ref) {
        String classPath = input.replace('.', '/');
        if (ref) {
            if (!input.startsWith("L")) classPath = 'L' + classPath;
            if (!input.endsWith(";")) classPath = classPath + ';';
        }
        return classPath;
    }

    public static String formatClassPathDescriptor(Field input, boolean ref) {
        return ClassBytecodeGenerator.formatClassPathDescriptor(input.getType(), ref);
    }

    public static String formatClassPathDescriptor(Class<?> input, boolean ref) {
        if (input == null) return "V";
        if (input.isPrimitive()) {
            if (input == void.class) return "V";
            if (input == int.class) return "I";
            if (input == long.class) return "J";
            if (input == float.class) return "F";
            if (input == double.class) return "D";
            if (input == boolean.class) return "Z";
            if (input == char.class) return "C";
            if (input == byte.class) return "B";
            if (input == short.class) return "S";
            throw new IllegalArgumentException("Unsupported primitive type: " + input);
        }
        String classPath = input.getName();
        if (input.isArray()) {
            Class<?> ct = input;
            while (ct.isArray()) ct = ct.getComponentType();
            if (ct.isPrimitive()) return classPath;
        }
        classPath = ClassBytecodeGenerator.formatClassPathDescriptor(classPath);
        if (ref) classPath = "L" + classPath + ";";
        return classPath;
    }

    public static String formatMethodDescriptorWithNotReturn(Class<?>... parameterTypes) {
        return ClassBytecodeGenerator.formatMethodDescriptor(null, parameterTypes);
    }

    public static String formatMethodDescriptorWithMethod(Method method) {
        return ClassBytecodeGenerator.formatMethodDescriptor(method.getReturnType(), method.getParameterTypes());
    }

    public static String formatMethodDescriptor(Class<?> returnType, Class<?>... parameterTypes) {
        if ((returnType == null || returnType == void.class) && (parameterTypes == null || parameterTypes.length == 0)) {
            return "()V";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Class<?> parameterType : parameterTypes) {
            sb.append(ClassBytecodeGenerator.formatClassPathDescriptor(parameterType, true));
        }
        sb.append(")");
        sb.append(ClassBytecodeGenerator.formatClassPathDescriptor(returnType, true));
        return sb.toString();
    }

    /**
     * @param name
     * @param parameterClass
     * @param parameterObject
     * @return
     * @throws Throwable
     */
    public abstract byte[] generate(String name, C parameterClass, O parameterObject) throws Throwable;
}
