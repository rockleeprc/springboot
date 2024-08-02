package org.example1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.StandardServletEnvironment;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EnableAspectJAutoProxy
@SpringBootApplication
@RestController
@Validated
public class HelloWorldApplication {
    public static void main(String[] args) {
        AnnotationConfigServletWebServerApplicationContext context = (AnnotationConfigServletWebServerApplicationContext) SpringApplication.run(HelloWorldApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        String property = environment.getProperty("StandardServletEnvironment.SERVLET_CONTEXT_PROPERTY_SOURCE_NAME");
        String property1 = environment.getProperty(StandardServletEnvironment.SERVLET_CONFIG_PROPERTY_SOURCE_NAME);
        System.out.println("");
    }

    @GetMapping("/echo")
    public String echo(@Valid @NotNull(message = "不能为空") String message,
                       @Valid @NotNull(message = "不能为空")String info) {
        return LocalDate.now() + "-" + message;
    }

    @PostMapping("/echo1")
    public String echo1(@Validated @RequestBody User user) {
        return LocalDate.now() + "-" + user;
    }
}
