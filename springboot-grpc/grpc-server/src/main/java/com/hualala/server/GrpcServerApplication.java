package com.hualala.server;

import com.alibaba.csp.sentinel.adapter.grpc.SentinelGrpcClientInterceptor;
import com.alibaba.csp.sentinel.adapter.grpc.SentinelGrpcServerInterceptor;
import com.hualala.server.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@Slf4j
@SpringCloudApplication
public class GrpcServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(GrpcServerApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
        System.out.println(context.getBean(SentinelGrpcServerInterceptor.class));
    }


    @PostConstruct
    public void run() {
        log.info("-----PostConstruct");
    }


    @Bean
    public Person person() {
        log.info("-----init person");
        return new Person("liyan", 10);
    }
}
