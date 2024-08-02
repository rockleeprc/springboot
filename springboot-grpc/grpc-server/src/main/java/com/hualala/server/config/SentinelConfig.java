package com.hualala.server.config;


import com.alibaba.csp.sentinel.adapter.grpc.SentinelGrpcServerInterceptor;
import com.hualala.grpc.server.annotation.GrpcGlobalServerInterceptor;
import io.grpc.ServerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class SentinelConfig {
    @GrpcGlobalServerInterceptor
    @Bean
    public ServerInterceptor sentinelGrpcServerInterceptor() {
        return new SentinelGrpcServerInterceptor();
    }

//    @Bean
//    public ClientInterceptor sentinelGrpcClientInterceptor() {
//        return new SentinelGrpcClientInterceptor();
//    }

//    @Bean
//    public GlobalClientInterceptorConfigurer globalInterceptorConfigurerAdapter(ClientInterceptor sentinelGrpcClientInterceptor) {
//        return registry -> registry.addClientInterceptors(sentinelGrpcClientInterceptor);
//    }
}

