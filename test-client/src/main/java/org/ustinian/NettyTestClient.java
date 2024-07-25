package org.ustinian;

import org.ustinian.serializer.CommonSerializer;
import org.ustinian.serializer.ProtobufSerializer;
import org.ustinian.transport.RpcClient;
import org.ustinian.transport.RpcClientProxy;
import org.ustinian.transport.netty.client.NettyClient;

public class NettyTestClient {
    public static void main(String[] args) {
        RpcClient client = new NettyClient(CommonSerializer.PROTOBUF_SERIALIZER);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject helloObject = new HelloObject(12, "This is a message");
        String res = helloService.hello(helloObject);
        System.out.println(res);
        System.out.println("****************************************");
    }
}
