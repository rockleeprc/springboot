package com.hualala.proto;

import com.hualala.proto.interceptor.EchoRequestClientHeadersInterceptor;
import io.grpc.ClientInterceptors;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


public class ClientBootstrap {
    public static void main(String[] args) {
        // 创建与Server端的channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 9999)
                .usePlaintext() // 纯文本类型
                .intercept(new EchoRequestClientHeadersInterceptor())
                .build();

        // 通过channel创建stub
        MathServiceGrpc.MathServiceBlockingStub stub = MathServiceGrpc.newBlockingStub(channel);
        // 调用add
        AddRequest addRequest = AddRequest.newBuilder().setA(4).setB(9).build();
        AddReply reply = stub.add(addRequest);
        System.out.println(reply.getRes());
        channel.shutdown();
    }

}
