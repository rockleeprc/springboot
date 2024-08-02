package com.foo.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor, PriorityOrdered, Ordered {
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
            MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
            propertyValues.add("name", "zhangsan");
        }

        BeanDefinition redDefinition = beanFactory.getBeanDefinition("com.foo.bean.Red");
        BeanDefinition yellowDefinition = beanFactory.getBeanDefinition("yellow");
        System.out.println(yellowDefinition);
    }

    @Override
    public int getOrder() {
        return 8;
    }
}
