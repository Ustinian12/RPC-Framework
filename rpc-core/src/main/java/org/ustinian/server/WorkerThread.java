package org.ustinian.server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ustinian.entity.RpcRequest;
import org.ustinian.entity.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class WorkerThread implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(WorkerThread.class);

    private Socket socket;
    private Object service;

    public WorkerThread(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        try(ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            RpcRequest request = (RpcRequest) ois.readObject();
            Method method = service.getClass().getMethod(request.getMethodName(),request.getParamTypes());
            Object returnObject = method.invoke(service, request.getParameters());
            oos.writeObject(RpcResponse.success(returnObject));
            oos.flush();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            logger.error("调用或发送时有错误发生：", e);
        }
    }
}
