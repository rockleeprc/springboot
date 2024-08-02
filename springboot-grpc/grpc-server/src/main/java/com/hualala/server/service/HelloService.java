package com.hualala.server.service;

import com.hualala.grpc.server.annotation.GrpcService;
import com.hualala.infrastructure.GreeterGrpc;
import com.hualala.infrastructure.HelloReply;
import com.hualala.infrastructure.HelloRequest;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@GrpcService
public class HelloService extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        log.info("----------" + request.getName());
        HelloReply res = doSayHelle(request);
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    private HelloReply doSayHelle(HelloRequest request) {
        HelloReply res = HelloReply.newBuilder().setMessage("hello " + request.getName() + ", this is test").build();
        return res;
    }
}
