package org.example9.config;

import org.example9.domain.Address;
import org.example9.domain.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanAutoConfiguration {

//    @Bean
//    public Person person() {
//        return new Person("tom", 19);
//    }

    @Bean
    public Address address() {
        return new Address("BJ", "ChangAnStreet");
    }

    @Bean
    public Car car() {
        return new Car("Benz");
    }
}
