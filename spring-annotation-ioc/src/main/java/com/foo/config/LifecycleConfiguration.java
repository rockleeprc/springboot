package com.foo.config;

import com.foo.bean.*;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifecycleConfiguration {

    //    @Scope("prototype")
    //    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Red red() {
        return new Red();
    }

    //    @Scope("prototype")
    //    @Bean
    public Blue blue() {
        return new Blue();
    }

    @Bean
    public Yellow yellow() {
        return new Yellow();
    }

}
