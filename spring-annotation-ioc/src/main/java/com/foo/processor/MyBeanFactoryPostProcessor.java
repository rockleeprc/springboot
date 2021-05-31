package com.foo.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("MyBeanFactoryPostProcessor-->" + beanDefinitionName);
        }

        BeanDefinition redDefinition = beanFactory.getBeanDefinition("com.foo.bean.Red");
        BeanDefinition yellowDefinition = beanFactory.getBeanDefinition("yellow");
        System.out.println(yellowDefinition);
    }
}
