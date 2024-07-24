package org.ustinian.transport.socket.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ustinian.RequestHandler;
import org.ustinian.transport.RpcServer;
import org.ustinian.provider.ServiceProvider;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class SocketRpcServer implements RpcServer {
    private final ExecutorService threadPool;
    private static final Logger logger = LoggerFactory.getLogger(SocketRpcServer.class);
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 50;
    private static final int KEEP_ALIVE_TIME = 60;
    private RequestHandler requestHandler = new RequestHandler();
    private final ServiceProvider serviceProvider;

    public SocketRpcServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, workQueue, threadFactory);
    }
    public void start(int port) {
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("服务器正在启动...");
            Socket socket;
            while((socket = serverSocket.accept()) != null) {
                logger.info("客户端连接！Ip为：" + socket.getInetAddress());
                threadPool.execute(new RequestHandlerThread(socket, requestHandler, serviceProvider));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() {

    }

    @Override
    public <T> void publishService(Object service, Class<T> serviceClass) {

    }
}
