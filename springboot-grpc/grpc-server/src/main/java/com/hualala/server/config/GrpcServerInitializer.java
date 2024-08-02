package com.hualala.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// CommandLineRunner
@Slf4j
//@Component
public class GrpcServerInitializer implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("-----ApplicationRunner");
    }
}
