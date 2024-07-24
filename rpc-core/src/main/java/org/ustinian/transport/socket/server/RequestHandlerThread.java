package org.ustinian.transport.socket.server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ustinian.RequestHandler;
import org.ustinian.entity.RpcRequest;
import org.ustinian.entity.RpcResponse;
import org.ustinian.provider.ServiceProvider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandlerThread implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandlerThread.class);

    private Socket socket;
    private RequestHandler requestHandler;
    private ServiceProvider serviceProvider;

    public RequestHandlerThread(Socket socket, RequestHandler requestHandler, ServiceProvider serviceProvider) {
        this.socket = socket;
        this.requestHandler = requestHandler;
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void run() {
        try(ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            RpcRequest request = (RpcRequest) ois.readObject();
            String interfaceName = request.getInterfaceName();
//            Object service = serviceProvider.getService(interfaceName);
//            Object returnObject = requestHandler.handle(request, service);
//            oos.writeObject(RpcResponse.success(returnObject));
            oos.flush();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("调用或发送时有错误发生：", e);
        }
    }
}
