package com.foo;

import com.foo.bean.Cat;
import com.foo.bean.Green;
import com.foo.bean.GreenFactoryBean;
import com.foo.bean.Person;
import com.foo.config.BeanConfiguration;
import com.foo.config.ProcessorConfiguration;
import com.foo.config.ProfileConfiguration;
import com.foo.servervice.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class Bootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfiguration.class);

        PersonService service = context.getBean(PersonService.class);
        service.print();

        context.close();
    }

    /**
     * 事件
     */
    public static void m3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProcessorConfiguration.class);
        context.publishEvent(new ApplicationEvent(new String("自定义事件")) {
        });
        Person person = context.getBean(Person.class);
        context.close();
    }

    /**
     * 编码加载profile
     */
    public static void m2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.register(ProfileConfiguration.class);
        context.refresh();
        print(context);
    }

    /**
     * @Profile 加载profile
     */
    public static void m1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProfileConfiguration.class);
        Map<String, Cat> cats = context.getBeansOfType(Cat.class);
        for (Map.Entry<String, Cat> entry : cats.entrySet()) {
            System.out.println(entry.getKey() + "---" + entry.getKey());
        }
        context.close();
    }

    /**
     * 打印context
     *
     * @param context
     */
    public static void print(ApplicationContext context) {
        Map<String, Cat> cats = context.getBeansOfType(Cat.class);
        for (Map.Entry<String, Cat> entry : cats.entrySet()) {
            System.out.println(entry.getKey() + "---" + entry.getKey());
        }

    }
}
