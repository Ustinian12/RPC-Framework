package org.ustinian;

import org.ustinian.provider.ServiceProviderImpl;
import org.ustinian.provider.ServiceProvider;
import org.ustinian.transport.socket.server.SocketRpcServer;

public class SocketTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceProvider serviceProvider = new ServiceProviderImpl();
//        serviceProvider.registerService(helloService);
//        SocketRpcServer socketRpcServer = new SocketRpcServer(serviceProvider);
//        socketRpcServer.start(9998);
    }
}
