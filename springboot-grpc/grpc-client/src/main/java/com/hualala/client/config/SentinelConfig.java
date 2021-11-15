package com.hualala.client.config;


import com.alibaba.csp.sentinel.adapter.grpc.SentinelGrpcClientInterceptor;
import com.hualala.grpc.client.annotation.GrpcGlobalClientInterceptor;
import io.grpc.ClientInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SentinelConfig {
    @GrpcGlobalClientInterceptor
    @Bean
    public ClientInterceptor sentinelGrpcClientInterceptor() {
        return new SentinelGrpcClientInterceptor();
    }


//    @Bean
//    public GlobalClientInterceptorConfigurer globalInterceptorConfigurerAdapter(ClientInterceptor sentinelGrpcClientInterceptor) {
//        return registry -> registry.addClientInterceptors(sentinelGrpcClientInterceptor);
//    }
}

