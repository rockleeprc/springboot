package com.foo.processor;

import com.foo.bean.Yellow;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("MyBeanDefinitionRegistryPostProcessor-->" + beanDefinitionName);
        }
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Yellow.class);
        builder.addPropertyValue("message", "黄色");
        registry.registerBeanDefinition("yellow", builder.getBeanDefinition());

        System.out.println("MyBeanDefinitionRegistryPostProcessor bean数量：" + registry.getBeanDefinitionCount());


    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
