package org.ustinian;

import org.ustinian.netty.server.NettyServer;
import org.ustinian.registry.DefaultServiceRegistry;
import org.ustinian.registry.ServiceRegistry;

public class NettyTestServer{
    public static void main(String[] args){
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry registry = new DefaultServiceRegistry();
        registry.registerService(helloService);
        NettyServer server = new NettyServer();
        server.start(9998);
    }
}
