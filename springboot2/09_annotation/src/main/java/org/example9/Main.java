package org.example9;

import org.example9.config.AImportAutoConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ApplicationListener> applicationListeners = SpringFactoriesLoader.loadFactories(ApplicationListener.class, Main.class.getClass().getClassLoader());
        for (ApplicationListener applicationListener : applicationListeners) {
            System.out.println(applicationListener);
        }
    }

    public static void m1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AImportAutoConfiguration.class);
        AImportAutoConfiguration bean = context.getBean(AImportAutoConfiguration.class);
        System.out.println("================================");
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }
}
