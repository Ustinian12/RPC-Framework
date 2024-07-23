package org.ustinian;

import org.ustinian.socket.client.SocketRpcClient;
import org.ustinian.entity.RpcRequest;
import org.ustinian.entity.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcClientProxy implements InvocationHandler {

    private final RpcClient client;
    public RpcClientProxy(RpcClient client) {
        this.client = client;
    }
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest(method.getDeclaringClass().getName(),
                method.getName(), args, method.getParameterTypes());
        return client.sendRequest(request);
    }
}
