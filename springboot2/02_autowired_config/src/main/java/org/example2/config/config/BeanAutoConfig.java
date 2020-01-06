package org.example2.config.config;

import org.example2.config.domain.Person;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanAutoConfig {

//    @Bean(value="p1")
//    @ConfigurationProperties(prefix = "person")
    public Person p1() {
        return new Person();
    }

    //@Bean(value="p2")
    //@Primary
    public Person p2() {
        return new Person("tom", 20);
    }
}
