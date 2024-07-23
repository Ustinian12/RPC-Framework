package org.ustinian;

import org.ustinian.registry.DefaultServiceRegistry;
import org.ustinian.registry.ServiceRegistry;
import org.ustinian.socket.server.SocketRpcServer;

public class SocketTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.registerService(helloService);
        SocketRpcServer socketRpcServer = new SocketRpcServer(serviceRegistry);
        socketRpcServer.start(9998);
    }
}
