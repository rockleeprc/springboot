package org.example9;

import org.example9.config.CustomerTypeExcludeFilter;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM, classes = CustomerTypeExcludeFilter.class))
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);

        //Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
//        PersonDao personDao = (PersonDao) context.getBean("personDao");
//        personDao.println();

//        Person person = context.getBean(Person.class);
//        System.out.println(person);

//        BeanAutoConfiguration bean = context.getBean(BeanAutoConfiguration.class);
//        System.out.println(bean);
//
//        Car car = context.getBean(Car.class);
//        System.out.println(car);

//        Address address = context.getBean(Address.class);
//        System.out.println(address);

        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println("====="+beanDefinitionName);
        }

        context.close();
    }
}
