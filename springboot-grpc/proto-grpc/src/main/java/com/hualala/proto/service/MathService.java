package com.hualala.proto.service;

import com.hualala.proto.AddReply;
import com.hualala.proto.AddRequest;
import com.hualala.proto.MathServiceGrpc;
import io.grpc.stub.StreamObserver;

public class MathService extends MathServiceGrpc.MathServiceImplBase {
    @Override
    public void add(AddRequest request, StreamObserver<AddReply> responseObserver) {
        System.out.println(Thread.currentThread().getName());
        int res = doAdd(request.getA(), request.getB());
        responseObserver.onNext(AddReply.newBuilder().setRes(res).build());
        responseObserver.onCompleted();
//        responseObserver.onError(); // 异常信息
    }

    private int doAdd(int a, int b) {
        return a + b;
    }
}
