package com.foo;

import com.foo.bean.Red;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class BeanDefinitionExample {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.refresh();

        // 1 builder
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Red.class);
        builder.addPropertyValue("message", "红色");
        builder.setInitMethodName("init");
        builder.setDestroyMethodName("destroy");
        AbstractBeanDefinition beanDefinition1 = builder.getBeanDefinition();

        // 2 实现类
        GenericBeanDefinition beanDefinition2 = new GenericBeanDefinition();
        beanDefinition2.setBeanClass(Red.class);
        MutablePropertyValues proerty = new MutablePropertyValues();
        proerty.add("message", "真红");
        beanDefinition2.setPropertyValues(proerty);


        // register context
        context.registerBeanDefinition("red", beanDefinition1);
        context.registerBeanDefinition("realred", beanDefinition2);


        Map<String, Red> map = context.getBeansOfType(Red.class);
        System.out.println(map);

        context.close();
    }
}
