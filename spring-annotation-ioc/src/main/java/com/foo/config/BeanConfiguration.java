package com.foo.config;


import com.foo.bean.GreenFactoryBean;
import com.foo.bean.Person;
import com.foo.bean.Red;
import com.foo.condition.LinuxCondition;
import com.foo.condition.WindowsCondition;
import com.foo.impord.MyImport;
import com.foo.impord.MyImportRegister;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


/*
不包含
@ComponentScan(basePackages="com.foo",excludeFilters = {@ComponentScan.Filter({Controller.class,Service.class})})
只包含，必须配置useDefaultFilters=false
@ComponentScan(basePackages = "com.foo",includeFilters = {@ComponentScan.Filter({Repository.class})},useDefaultFilters=false)
完整写法
@ComponentScan(basePackages = "com.foo",includeFilters = {@ComponentScan.Filter(type=FilterType.ANNOTATION,classes={Repository.class})},useDefaultFilters=false)
 */
@Configuration
@ComponentScan(basePackages = "com.foo")
@ComponentScan(value = {"", ""}, includeFilters = @ComponentScan.Filter(value = {Controller.class, Service.class}), useDefaultFilters = false)
@Import({Red.class, MyImport.class, MyImportRegister.class})
public class BeanConfiguration {

    @Lazy
    @Bean
    public Person person() {
        System.out.println("--------");
        return new Person("lisi", 1);
    }

    @Conditional(WindowsCondition.class)
    @Bean
    public Person bill() {
        return new Person("bill", 60);
    }

    @Conditional(LinuxCondition.class)
    @Bean
    public Person linus() {
        return new Person("linus", 50);
    }

    @Bean
    public GreenFactoryBean green() {
        return new GreenFactoryBean();
    }

    @Bean
    public SmartInitializingSingleton smartInitializingSingleton() {

        return new SmartInitializingSingleton() {
            @Autowired
            private DefaultListableBeanFactory defaultListableBeanFactory;

            @Override
            public void afterSingletonsInstantiated() {
                System.out.println("99999999999999" + defaultListableBeanFactory.getSingletonCount());
            }
        };
    }
}
/*
@Bean+@Condition+@Scope
@ComponentScan
    @Controller
    @Service
    @Repository
    @Component
@Import
    xxx.class
    ImportSelector
    ImportBeanDefinitionRegistrar
 */