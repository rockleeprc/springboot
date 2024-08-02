package com.foo.processor;

import com.foo.bean.Yellow;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization...");
        if (bean instanceof Yellow) {
            Yellow yellow = (Yellow) bean;
            yellow.setMessage("---hello processor");
            return yellow;
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization...");
        if (bean instanceof Yellow) {
            Yellow yellow = (Yellow) bean;
//            yellow.setMessage("---hello processor");
//            return yellow;
        }
        return bean;
    }
}
