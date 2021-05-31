package com.foo;

import com.foo.bean.Cat;
import com.foo.bean.Person;
import com.foo.bean.Yellow;
import com.foo.config.ProcessorConfiguration;
import com.foo.config.ProfileConfiguration;
import com.foo.service.PersonService;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Bootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProcessorConfiguration.class);

        Yellow yellow = context.getBean(Yellow.class);
        System.out.println(yellow);
        context.close();
    }

    private static final Map<String, String> aliasMap = new ConcurrentHashMap<>(16);

    static {
        aliasMap.put("AA", "AAA");
        aliasMap.put("A", "AA");
        aliasMap.put("AAA", "BB");
    }

    /**
     * 有趣的map去重方法
     *
     * @param name
     * @return
     */
    public static String canonicalName(String name) {
        String result = name;
        String temp;
        do {
            temp = aliasMap.get(result);
            if (temp != null) {
                result = temp;
            }
        } while (temp != null);
        return result;
    }

    /**
     * BeanDefinition 生成方式
     */
    public static void m4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.refresh();

        // 1 BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Yellow.class);
        beanDefinitionBuilder.addPropertyValue("message", "黄色");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // 2 GenericBeanDefinition
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(Yellow.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("message", "真黄色");
        genericBeanDefinition.setPropertyValues(propertyValues);

        context.registerBeanDefinition("yellow", beanDefinition);
//        context.registerBeanDefinition("realyellow", genericBeanDefinition);
        BeanDefinitionReaderUtils.registerWithGeneratedName(genericBeanDefinition, context);
        Map<String, Yellow> map = context.getBeansOfType(Yellow.class);
        System.out.println(map);
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
