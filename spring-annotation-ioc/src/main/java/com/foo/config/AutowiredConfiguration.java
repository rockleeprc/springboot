package com.foo.config;

import com.foo.bean.Cat;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan({"com.foo.bean"})
public class AutowiredConfiguration {


    @Bean
    public Cat cat1() {
        return new Cat("加菲猫");
    }

    @Bean
    @Primary
    public Cat cat2() {
        return new Cat("机器猫");
    }
}
