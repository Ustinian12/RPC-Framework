package org.ustinian.transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ustinian.entity.RpcRequest;
import org.ustinian.entity.RpcResponse;
import org.ustinian.transport.netty.client.NettyClient;
import org.ustinian.transport.socket.client.SocketRpcClient;
import org.ustinian.util.RpcMessageChecker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RpcClientProxy implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(RpcClientProxy.class);

    private final RpcClient client;
    public RpcClientProxy(RpcClient client) {
        this.client = client;
    }
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest(UUID.randomUUID().toString(), method.getDeclaringClass().getName(),
                method.getName(), args, method.getParameterTypes(), false);
        RpcResponse rpcResponse = null;
        if (client instanceof NettyClient) {
            CompletableFuture<RpcResponse> completableFuture = (CompletableFuture<RpcResponse>) client.sendRequest(request);
            try {
                rpcResponse = completableFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                logger.error("方法调用请求发送失败", e);
                return null;
            }
        }
        if (client instanceof SocketRpcClient) {
            rpcResponse = (RpcResponse) client.sendRequest(request);
        }
        RpcMessageChecker.check(request, rpcResponse);
        return rpcResponse.getData();
    }
}
