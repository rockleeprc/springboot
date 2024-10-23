package org.example9.config;

import org.example9.domain.Address;
import org.example9.domain.Car;
import org.example9.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanAutoConfiguration {

    public BeanAutoConfiguration(){
        System.out.println("BeanAutoConfiguration 初始化");
    }

    @Bean
    public Person person() {
        return new Person("tom", 19);
    }

    @Bean
    public Address address() {
        return new Address("BJ", "ChangAnStreet");
    }

    @Bean
    public Car car() {
        return new Car("Benz");
    }
}
