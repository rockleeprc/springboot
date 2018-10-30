package com.foo;

import com.foo.bean.*;
import com.foo.config.BeanConfiguration;
import com.foo.config.LifecycleConfiguration;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class Bootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifecycleConfiguration.class);
        Yellow yellow = (Yellow) context.getBean("yellow");
        System.out.println(yellow.getMessage());
        context.close();
    }
}
