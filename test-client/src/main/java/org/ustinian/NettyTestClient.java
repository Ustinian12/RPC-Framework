package org.ustinian;

import org.ustinian.netty.client.NettyClient;

public class NettyTestClient {
    public static void main(String[] args) {
        RpcClient rpcClient = new NettyClient("127.0.0.1", 9998);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcClient);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject helloObject = new HelloObject(12, "This is a message");
        String res = helloService.hello(helloObject);
        System.out.println(res);
    }
}
