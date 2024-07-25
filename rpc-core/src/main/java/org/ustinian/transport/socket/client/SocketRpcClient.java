package org.ustinian.transport.socket.client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ustinian.serializer.CommonSerializer;
import org.ustinian.transport.RpcClient;
import org.ustinian.entity.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketRpcClient implements RpcClient {

    private static final Logger logger = LoggerFactory.getLogger(SocketRpcClient.class);

    private final String host;
    private final int port;

    public SocketRpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object sendRequest(RpcRequest rpcRequest) {
        try(Socket socket = new Socket(host, port)) {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(rpcRequest);
            out.flush();
            return in.readObject();
        }catch (IOException | ClassNotFoundException e) {
            logger.error("调用时有错误发生：", e);
            return null;
        }
    }


}
