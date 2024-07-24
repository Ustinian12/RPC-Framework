package org.ustinian;

import org.ustinian.transport.RpcClientProxy;
import org.ustinian.transport.socket.client.SocketRpcClient;

public class SocketTestClient {
    public static void main(String[] args) {
        SocketRpcClient client = new SocketRpcClient("127.0.0.1", 9998);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject helloObject = new HelloObject(12, "Hello World");
        String res = helloService.hello(helloObject);
        System.out.println(res);
    }
}
