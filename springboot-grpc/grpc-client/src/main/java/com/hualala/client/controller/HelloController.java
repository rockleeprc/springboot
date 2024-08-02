package com.hualala.client.controller;

import com.alibaba.csp.sentinel.adapter.grpc.SentinelGrpcClientInterceptor;
import com.hualala.client.annotation.Caching;
import com.hualala.grpc.client.annotation.GrpcClient;
import com.hualala.infrastructure.GreeterGrpc;
import com.hualala.infrastructure.HelloReply;
import com.hualala.infrastructure.HelloRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @GrpcClient("hello-grpc-server")
    private GreeterGrpc.GreeterBlockingStub blockingStub;

    @Caching
    @RequestMapping("/hello")
    public String hello() {
        log.info("com.hualala.client.controller.HelloController.hello");
        HelloReply helloReply = blockingStub.withInterceptors(new SentinelGrpcClientInterceptor()).sayHello(HelloRequest.newBuilder().setName("aaa").build());
        log.info(helloReply.getMessage());
        return helloReply.getMessage();
    }
}
