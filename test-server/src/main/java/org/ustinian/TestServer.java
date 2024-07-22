package org.ustinian;

import org.ustinian.server.RpcServer;

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(helloService,9998);
    }
}
