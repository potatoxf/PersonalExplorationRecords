/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core.template.impl;

import com.github.potatoxf.catalytic.core.Transferor;
import com.github.potatoxf.catalytic.core.template.ClassBytecodeGenerator;
import com.github.potatoxf.catalytic.utils.reflect.AttributeInfo;
import com.github.potatoxf.catalytic.utils.reflect.AttributeName;
import com.github.potatoxf.catalytic.utils.reflect.Reflects;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author potatoxf
 */
public class TransferorClassBytecodeGenerator extends ClassBytecodeGenerator<TransferorParameterClass, TransferorParameterObject> {

    @Override
    public boolean isSupport(TransferorParameterClass parameterClass, TransferorParameterObject parameterObject) {
        return true;
    }

    @Override
    public byte[] generate(String name, TransferorParameterClass parameterClass, TransferorParameterObject parameterObject) throws Throwable {
        Class<?> from = parameterClass.origin();
        Class<?> to = parameterClass.target();
        String classpath = formatClassPathDescriptor(name);
        String scp = formatClassPathDescriptor(Transferor.class, false);
        String ocp = formatClassPathDescriptor(from, false);
        String tcp = formatClassPathDescriptor(to, false);
        // (1) 创建ClassWriter对象
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        {

            cw.visit(V1_8, ACC_PUBLIC + ACC_FINAL,
                    classpath,
                    "L" + scp + "<L" + ocp + ";L" + tcp + ";>;",
                    scp,
                    null
            );
        }

        MethodVisitor mv = null;

        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, scp, "<init>", "()V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        {
            //桥接方法
            mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE, "apply", formatMethodDescriptor(Object.class, Object.class, Object.class), null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1); // 加载第一个参数
            mv.visitTypeInsn(CHECKCAST, ocp);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitTypeInsn(CHECKCAST, tcp);
            mv.visitMethodInsn(INVOKEVIRTUAL, classpath, "apply", formatMethodDescriptor(to, from, to), false);
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN); // 返回参数
            mv.visitMaxs(3, 3); // 1是栈的最大深度，2是局部变量表的大小
            mv.visitEnd();
        }

        Map<AttributeName, AttributeInfo> fromAttribute = Reflects.listAttributeMap(from);
        Map<AttributeName, AttributeInfo> toAttribute = Reflects.listAttributeMap(to);
        List<AttributeName> list = new ArrayList<>(fromAttribute.keySet());
        list.retainAll(toAttribute.keySet());

        {
            mv = cw.visitMethod(ACC_PUBLIC, "apply", formatMethodDescriptor(to, from, to), null, null);
            mv.visitCode();
            for (AttributeName attributeName : list) {
                AttributeInfo fromAttributeInfo = fromAttribute.get(attributeName);
                AttributeInfo toAttributeInfo = toAttribute.get(attributeName);
                mv.visitVarInsn(ALOAD, 1);
                mv.visitMethodInsn(INVOKEVIRTUAL, ocp, fromAttributeInfo.getGetterName(), formatMethodDescriptorWithMethod(fromAttributeInfo.getGetterMethod()), false);
                mv.visitVarInsn(ASTORE, 3);
                mv.visitVarInsn(ALOAD, 3);
                Label label = new Label();
                mv.visitJumpInsn(IFNULL, label);
                mv.visitVarInsn(ALOAD, 2);
                mv.visitVarInsn(ALOAD, 3);
                mv.visitMethodInsn(INVOKEVIRTUAL, tcp, toAttributeInfo.getSetterName(), formatMethodDescriptorWithMethod(toAttributeInfo.getSetterMethod()), false);
                mv.visitLabel(label);
            }
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(ARETURN); // 返回参数
            mv.visitMaxs(3, 3); // 1是栈的最大深度，2是局部变量表的大小
            mv.visitEnd();
        }
        cw.visitEnd(); // 注意，最后要调用visitEnd()方法

        // (3) 调用toByteArray()方法
        return cw.toByteArray();
    }

}
