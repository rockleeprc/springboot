package com.foo.config;

import com.foo.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:person.properties")
public class PropertyConfiguration {

    @Bean
    public Person person() {
        return new Person();
    }
}
