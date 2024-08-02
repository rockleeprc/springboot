package com.grpc.server.service;

import com.demo.Hello;
import com.demo.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class HelloGrpcService extends HelloServiceGrpc.HelloServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(HelloGrpcService.class);

    @Override
    public void hello(Hello.HelloRequest request, StreamObserver<Hello.HelloResponse> responseObserver) {
        logger.info("hello start");
        final Hello.HelloResponse.Builder replyBuilder = Hello.HelloResponse.newBuilder().setName(request.getName()).setStatus("success");
        responseObserver.onNext(replyBuilder.build());
        responseObserver.onCompleted();
    }
}
