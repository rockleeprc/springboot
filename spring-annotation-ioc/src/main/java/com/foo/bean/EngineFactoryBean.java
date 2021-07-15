package com.foo.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class EngineFactoryBean implements FactoryBean<Engine>, BeanNameAware, InvocationHandler {

    private String name;

    @Override
    public Engine getObject() throws Exception {
        System.out.println("EngineFactoryBean  to build :" + name);
        Engine engine = (Engine) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Engine.class}, this);
        System.out.println(engine);
        return engine;
    }

    @Override
    public Class<?> getObjectType() {
        return Engine.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("EngineFactoryBean beanName=" + name);
        this.name = name;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("here is invoke  engine:" + method.getName());
        return null;
    }


    public static void main(String[] args) {
        Object eee = Proxy.newProxyInstance(EngineFactoryBean.class.getClassLoader(), new Class[]{Engine.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(proxy.getClass());
                return proxy;
            }
        });
        System.out.println(eee);
    }
}
