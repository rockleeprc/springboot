package com.foo.config;

import com.foo.bean.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfiguration {

    @Profile("test")
    @Bean
    public Cat catTest() {
        return new Cat("Test");
    }

    @Profile("dev")
    @Bean
    public Cat catDev() {
        return new Cat("Dev");
    }

    @Profile("prod")
    @Bean
    public Cat catProd() {
        return new Cat("Prod");
    }
}
