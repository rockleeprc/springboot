package org.example1;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@SpringBootApplication
@RestController
public class HelloWorldApplication {
    public static void main(String[] args) {
//        SpringApplication.run(HelloWorldApplication.class, args);
        SpringApplication application = new SpringApplication(HelloWorldApplication.class);
        application.setBannerMode(Banner.Mode.CONSOLE);
        application.run(args);
    }

    @RequestMapping("/echo")
    public String echo(String message) {
        return LocalDate.now() + "-" + message;
    }
}
