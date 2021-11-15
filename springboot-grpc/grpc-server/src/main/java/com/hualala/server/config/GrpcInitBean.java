package com.hualala.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class GrpcInitBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("-----GrpcInitBean");
    }
}
