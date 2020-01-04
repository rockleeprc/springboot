package org.example2.config.config;

import org.example2.config.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config {

    @Bean(value="p1")
    public Person p1() {
        return new Person("marry", 12);
    }

    @Bean(value="p2")
    @Primary
    public Person p2() {
        return new Person("tom", 20);
    }
}
