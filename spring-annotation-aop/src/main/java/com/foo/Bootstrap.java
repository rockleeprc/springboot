package com.foo;

import com.foo.config.AopConfiguration;
import com.foo.service.CalcService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


public class Bootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfiguration.class);
        CalcService service = context.getBean(CalcService.class);
        service.div(10, 1);
    }

}
