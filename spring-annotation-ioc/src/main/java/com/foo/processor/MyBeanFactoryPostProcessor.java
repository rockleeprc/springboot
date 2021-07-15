package com.foo.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor 被调用 ");
//        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println("MyBeanFactoryPostProcessor-->" + beanDefinitionName);
//        }

        // 不推荐使用getBean()实力化bean
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("person");
        if (beanDefinition != null) {
            beanDefinition.getPropertyValues().add("name", "zhangsan");
        }

    }
}
