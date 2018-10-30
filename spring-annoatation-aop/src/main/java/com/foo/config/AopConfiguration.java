package com.foo.config;

import com.foo.aspect.LogAspect;
import com.foo.service.CalcService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfiguration {

    @Bean
    public CalcService calcService() {
        return new CalcService();
    }

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
