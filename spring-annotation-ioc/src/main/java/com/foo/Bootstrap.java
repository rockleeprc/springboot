package com.foo;

import com.foo.bean.*;
import com.foo.config.AutowiredConfiguration;
import com.foo.config.BeanConfiguration;
import com.foo.config.LifecycleConfiguration;
import com.foo.config.PropertyConfiguration;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class Bootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutowiredConfiguration.class);
        Person person = (Person) context.getBean("person");
        System.out.println(person);

        context.close();
    }
}
