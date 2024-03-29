package com.foo.config;

import com.foo.bean.BenzCar;
import com.foo.bean.Engine;
import com.foo.bean.Red;
import com.foo.processor.MyBeanFactoryPostProcessor;
import com.foo.processor.SpecialBeanForEngineProcessor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(Red.class)
@Configuration
public class ProcessorConfiguration {
//    @Bean
//    public BeanPostProcessor beanPostProcessor() {
//        return new MyBeanPostProcessor();
//    }


    @Bean
    public BeanFactoryPostProcessor specialBeanForEngineProcessor() {
        return new SpecialBeanForEngineProcessor();
    }


    @Bean(initMethod = "start")
    BenzCar benzCar(Engine engine) {
        BenzCar car = new BenzCar();
        car.setEngine(engine);
        return car;
    }

    @Bean
    public BeanFactoryPostProcessor myBeanFactoryPostProcessor() {
        return new MyBeanFactoryPostProcessor();
    }
//
//    @Bean
//    public Person person() {
//        return new Person();
//    }
//
//    @Bean
//    public Cat cat() {
//        return new Cat();
//    }

//    @Bean
//    public BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
//        return new MyBeanDefinitionRegistryPostProcessor();
//    }
//
//    @Bean
//    public ApplicationListener applicationListener() {
//        return new MyApplicationListener();
//    }
//
//    @Bean
//    public ApplicationContextInitializer applicationContextInitializer() {
//        return new SpringApplicationContextInitializer();
//    }

}
