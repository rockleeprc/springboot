package com.foo.bean;

public class Red {
    private String message;

    public Red() {
        System.out.println("Red.Read()...");
    }

    public void init() {
        System.out.println("Red.init()...");
    }

    public void destroy() {
        System.out.println("Red.destroy()...");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Red{" +
                "message='" + message + '\'' +
                '}';
    }
}
