/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core.template;

import com.github.potatoxf.catalytic.utils.PathClassLoader;
import com.github.potatoxf.catalytic.converter.core.Transferor;
import com.github.potatoxf.catalytic.converter.core.template.impl.TransferorClassBytecodeGenerator;
import com.github.potatoxf.catalytic.converter.core.template.impl.TransferorClassNameGenerator;
import com.github.potatoxf.catalytic.converter.core.template.impl.TransferorTemplateGenerator;
import org.junit.Test;

import java.nio.file.Paths;

public class TestTemplate {

    @Test
    public void main() throws Exception {
        //可以通过下面的代码加载生成的类
        PathClassLoader pathClassLoader = new PathClassLoader(Paths.get("D:\\out"));
        TransferorTemplateGenerator template = new TransferorTemplateGenerator(pathClassLoader);
        template.registerClassBytecodeGenerator(new TransferorClassBytecodeGenerator());
        template.registerClassNameGenerator(new TransferorClassNameGenerator());

        com.github.potatoxf.catalytic.core.template.TestUserDo testUserDo = new com.github.potatoxf.catalytic.core.template.TestUserDo();
        com.github.potatoxf.catalytic.core.template.TestUserVo userVo = new com.github.potatoxf.catalytic.core.template.TestUserVo();
        testUserDo.name = "name";
        testUserDo.age = 18;
        Transferor<com.github.potatoxf.catalytic.core.template.TestUserDo, com.github.potatoxf.catalytic.core.template.TestUserVo> transferor = template.loadTemplateInstance(testUserDo, userVo);
        System.out.println(transferor.getClass().getName());
        transferor.apply(testUserDo, userVo);
        System.out.println(testUserDo);
        System.out.println(userVo);
    }
}
