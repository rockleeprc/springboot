package com.hualala.proto;

import com.hualala.proto.interceptor.MyInterceptor;
import com.hualala.proto.service.MathService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

import java.io.IOException;

public class ServerBootstrap {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9999)
                .addService(ServerInterceptors.intercept(new MathService(), new MyInterceptor()))
                .build()
                .start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            if (server != null) {
                server.shutdown();
            }
            System.err.println("*** server shut down");
        }));

        server.awaitTermination();
    }
}
