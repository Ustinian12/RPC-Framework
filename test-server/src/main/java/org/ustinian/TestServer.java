package org.ustinian;

import org.ustinian.registry.DefaultServiceRegistry;
import org.ustinian.registry.ServiceRegistry;
import org.ustinian.server.RpcServer;

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.registerService(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start(9998);
    }
}
