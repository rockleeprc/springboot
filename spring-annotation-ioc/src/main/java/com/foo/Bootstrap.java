package com.foo;

import com.foo.bean.Person;
import com.foo.bean.Red;
import com.foo.config.BeanConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class Bootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        String[] names = context.getBeanDefinitionNames();
        for (String bean : names) {
            System.out.println(bean);
        }

        Red red = context.getBean(Red.class);
        System.out.println(red);
    }
}
