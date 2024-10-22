package org.example9.config;


import org.example9.annotation.EnableImport;
import org.example9.domain.Car;
import org.springframework.context.annotation.Bean;

@EnableImport
//@Import({CImportSelect.class,BImportSelect.class,AImportSelect.class})
public class AImportAutoConfiguration {
    @Bean
    public Car car(){
        System.out.println("AImportAutoConfiguration#car");
        return new Car();
    }
}
