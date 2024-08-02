package com.hualala.proto.interceptor;

import io.grpc.*;

public class EchoRequestServerHeadersInterceptor implements ServerInterceptor {

    // 服务端header的key
    static final Metadata.Key<String> CUSTOM_HEADER_KEY = Metadata.Key.of("serverHeader", Metadata.ASCII_STRING_MARSHALLER);

    /**
     * @param call    ServerCall 对象，包含客户端请求的 MethodDescriptor
     * @param headers 请求头信息
     * @param next    处理链条上的下一个处理
     * @param <ReqT>
     * @param <RespT>
     * @return
     */
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        System.out.println("---------" + this.getClass().getName());

        System.out.println("client headers:" + headers);
        return next.startCall(new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {
            @Override
            public void sendHeaders(Metadata responseHeaders) {
                // 增加server端header信息
                responseHeaders.put(CUSTOM_HEADER_KEY, "this is server message");
                super.sendHeaders(responseHeaders);
            }
        }, headers);
    }
}
