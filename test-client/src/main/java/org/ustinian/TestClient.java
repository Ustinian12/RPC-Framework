package org.ustinian;

import org.ustinian.client.RpcClientProxy;

import java.lang.reflect.Proxy;

public class TestClient {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy("127.0.0.1", 9998);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject helloObject = new HelloObject(12, "Hello World");
        String res = helloService.hello(helloObject);
        System.out.println(res);
    }
}
