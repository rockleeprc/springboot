package com.foo.config;

import com.foo.bean.Red;
import com.foo.processor.MyApplicationListener;
import com.foo.processor.MyBeanDefinitionRegistryPostProcessor;
import com.foo.processor.MyBeanFactoryPostProcessor;
import com.foo.processor.MyBeanPostProcessor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(Red.class)
@Configuration
public class ProcessorConfiguration {
    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new MyBeanPostProcessor();
    }

    @Bean
    public BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new MyBeanFactoryPostProcessor();
    }

    @Bean
    public BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return new MyBeanDefinitionRegistryPostProcessor();
    }

    @Bean
    public ApplicationListener applicationListener() {
        return new MyApplicationListener();
    }

    @Bean
    public ApplicationContextInitializer applicationContextInitializer() {
        return new SpringApplicationContextInitializer();
    }

}
