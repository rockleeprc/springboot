package com.hualala.proto.interceptor;

import io.grpc.*;
import io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener;

public class EchoRequestClientHeadersInterceptor implements ClientInterceptor {
    // 客户端header的key
    static final Metadata.Key<String> CUSTOM_HEADER_KEY = Metadata.Key.of("clientHeader", Metadata.ASCII_STRING_MARSHALLER);

    /**
     * @param method      MethodDescriptor类型，标示请求方法。包括方法全限定名称、请求服务名称、请求、结果、序列化工具、幂等等。
     * @param callOptions 此次请求的附带信息。
     * @param next        执行此次RPC请求的抽象链接管道（Channel）
     * @param <ReqT>
     * @param <RespT>
     * @return
     */
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        System.out.println("---------" + this.getClass().getName());

        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {

            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                // 增加client端header信息
                headers.put(CUSTOM_HEADER_KEY, "this is client message");
                super.start(new SimpleForwardingClientCallListener<RespT>(responseListener) {
                    @Override
                    public void onHeaders(Metadata headers) {
                        // 输出服务端传递回来的header
                        System.out.println(headers);
                        super.onHeaders(headers);
                    }
                }, headers);
            }
        };
    }

    static class CustomCallListener<RespT> extends ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT> {

        protected CustomCallListener(ClientCall.Listener<RespT> delegate) {
            super(delegate);
        }
    }

    static class CustomForwardingClientCall<ReqT, RespT> extends ClientInterceptors.CheckedForwardingClientCall<ReqT, RespT> {

        protected CustomForwardingClientCall(ClientCall<ReqT, RespT> delegate) {
            super(delegate);
        }

        @Override
        protected void checkedStart(Listener<RespT> responseListener, Metadata headers) throws Exception {
            CustomCallListener<RespT> listener = new CustomCallListener<>(responseListener);
            delegate().start(listener, headers);
        }
    }
}
