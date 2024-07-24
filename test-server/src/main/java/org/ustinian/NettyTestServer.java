package org.ustinian;

import org.ustinian.serializer.ProtobufSerializer;
import org.ustinian.transport.netty.server.NettyServer;
import org.ustinian.provider.ServiceProviderImpl;
import org.ustinian.provider.ServiceProvider;

public class NettyTestServer{
    public static void main(String[] args){
        HelloService helloService = new HelloServiceImpl();
        NettyServer server = new NettyServer("127.0.0.1", 9997);
        server.setSerializer(new ProtobufSerializer());
        server.publishService(helloService, HelloService.class);
    }
}
