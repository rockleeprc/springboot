package com.foo.beanfactoryorder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.PriorityOrdered;

public class FirstBeanFactoryPostProcessor implements BeanFactoryPostProcessor, PriorityOrdered {
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("FirstBeanPostProcessor before");
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("FirstBeanPostProcessor after");
//        return bean;
//    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE+1;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("FirstBeanFactoryPostProcessor");
    }
}
