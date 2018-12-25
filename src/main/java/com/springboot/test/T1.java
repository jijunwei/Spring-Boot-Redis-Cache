package com.springboot.test;

import java.lang.reflect.ParameterizedType;

public class T1<T> {
    private Class classt;

    public T1() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.classt = (Class) type.getActualTypeArguments()[0];
        System.out.println(this.classt);
    }
}
