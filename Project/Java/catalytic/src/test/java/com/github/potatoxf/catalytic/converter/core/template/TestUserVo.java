/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core.template;

/**
 * @author potatoxf
 */
public class TestUserVo {
    public String name;
    public Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
