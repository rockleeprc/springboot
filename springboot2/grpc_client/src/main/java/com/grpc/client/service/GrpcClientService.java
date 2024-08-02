package com.grpc.client.service;

import com.demo.Hello;
import com.demo.HelloServiceGrpc;
import io.grpc.Channel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService {

    @GrpcClient("hello-grpc-server")
    private Channel serverChannel;

    public String hello(String name) {
        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(serverChannel);
        Hello.HelloRequest.Builder builder = Hello.HelloRequest.newBuilder().
                setName("xiaoli");
        Hello.HelloResponse response = stub.hello(builder.build());
        return "{'responseStatus':'" + response.getStatus() + "','result':[]}";
    }
}
