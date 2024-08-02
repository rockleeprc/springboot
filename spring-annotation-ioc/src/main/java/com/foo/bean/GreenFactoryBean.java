package com.foo.bean;

import org.springframework.beans.factory.FactoryBean;

public class GreenFactoryBean implements FactoryBean<Green> {
    public Green getObject() throws Exception {
        System.out.println("...........getObject()");
        return new Green();
    }

    public Class<?> getObjectType() {
        System.out.println("...........getObjectType()");
        return Green.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
