package org.example9;

import org.example9.annotation.EnableBean;
import org.example9.domain.Address;
import org.example9.domain.Car;
import org.example9.domain.Person;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.example9.dao"})
@EnableBean
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);

        //Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
//        PersonDao personDao = (PersonDao) context.getBean("personDao");
//        personDao.println();

        Person person = context.getBean(Person.class);
        Car car = context.getBean(Car.class);
        Address address = context.getBean(Address.class);
        System.out.println(person);
        System.out.println(car);
        System.out.println(address);

        context.close();
    }
}
