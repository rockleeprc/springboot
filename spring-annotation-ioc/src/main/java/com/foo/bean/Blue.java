package com.foo.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Blue implements InitializingBean,DisposableBean {

    public Blue(){
        System.out.println("Blue contructor...");
    }

    public void destroy() throws Exception {
        System.out.println("blue destroy...");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("blue afterPropertiesSet...");
    }
}
