package com.hualala.proto.interceptor;

import io.grpc.*;

import java.util.Set;


public class MyInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        System.out.println("---------"+this.getClass().getName());

        // method、service name
        MethodDescriptor<ReqT, RespT> methodDescriptor = call.getMethodDescriptor();
        String fullMethodName = methodDescriptor.getFullMethodName();
        String serviceName = methodDescriptor.getServiceName();


        System.out.println(fullMethodName);
        System.out.println(serviceName);

        // 获取头信息
        Set<String> keys = headers.keys();
        for (String key : keys) {
            System.out.println("key--" + key);
        }
        String address = headers.get(Metadata.Key.of("address", Metadata.ASCII_STRING_MARSHALLER));
        System.out.println(address);

        return next.startCall(call, headers);
    }
}
