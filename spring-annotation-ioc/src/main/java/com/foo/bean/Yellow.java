package com.foo.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Yellow {

    private String message;

    public Yellow() {
        System.out.println("yellow constructor...");
    }

    @PostConstruct
    public void init() {
        System.out.println("yellow init...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("yellow destroy...");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Yellow{" +
                "message='" + message + '\'' +
                '}';
    }
}
