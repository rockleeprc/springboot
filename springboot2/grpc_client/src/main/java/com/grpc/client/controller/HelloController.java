package com.grpc.client.controller;

import com.grpc.client.service.GrpcClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private GrpcClientService grpcClientService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        try {
            String result = grpcClientService.hello("a");
            logger.debug(" respString : {}", result);
            return result;
        } catch (Throwable e) {
            logger.error("hello error", e);
        }
        return null;
    }
}
