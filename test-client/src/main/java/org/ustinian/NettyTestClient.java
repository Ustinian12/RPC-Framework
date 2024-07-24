package org.ustinian;

import org.ustinian.serializer.ProtobufSerializer;
import org.ustinian.transport.RpcClient;
import org.ustinian.transport.RpcClientProxy;
import org.ustinian.transport.netty.client.NettyClient;

public class NettyTestClient {
    public static void main(String[] args) {
        RpcClient rpcClient = new NettyClient();
        rpcClient.setSerializer(new ProtobufSerializer());
        RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcClient);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject helloObject = new HelloObject(12, "This is a message");
        String res = helloService.hello(helloObject);
        System.out.println(res);
    }
}
