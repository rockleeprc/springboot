package org.b;

import org.b.bean.Green;
import org.b.bean.Red;
import org.b.bean.Yellow;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * @Import 使用
 * 1、直接导入一个bean
 * 2、实现ImportSelector接口
 * 3、实现ImportBeanDefinitionRegistrar接口
 */
@SpringBootApplication
public class ImportApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ImportApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        System.out.println(context.getBean(Green.class));
        System.out.println(context.getBean(Red.class));
        System.out.println(context.getBean(Yellow.class));

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);

        context.close();
    }
}
