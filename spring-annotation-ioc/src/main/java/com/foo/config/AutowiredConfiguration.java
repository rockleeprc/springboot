package com.foo.config;

import com.foo.bean.Cat;
import com.foo.bean.Man;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan({"com.foo.bean"})
public class AutowiredConfiguration {


    @Bean
    @Primary
    public Cat cat1() {
        return new Cat("加菲猫");
    }

    @Bean
    public Cat cat2() {
        return new Cat("机器猫");
    }


    @Bean
    public Man man(@Qualifier("cat2") Cat cat){
        return new Man(cat);
    }

}
