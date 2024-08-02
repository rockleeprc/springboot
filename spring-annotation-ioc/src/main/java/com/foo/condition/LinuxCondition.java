package com.foo.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxCondition implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        ClassLoader classLoader = context.getClassLoader();

        BeanDefinitionRegistry registry = context.getRegistry();

        ResourceLoader resourceLoader = context.getResourceLoader();

        Environment environment = context.getEnvironment();

        boolean hasPerson = registry.containsBeanDefinition("person");
        if (hasPerson) {
            return true;
        }

        String os = environment.getProperty("os.name");
        if (os.contains("linux")) {
            return true;
        }

        return false;
    }
}
