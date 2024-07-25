package org.ustinian.transport.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ustinian.enumeration.RpcError;
import org.ustinian.exception.RpcException;
import org.ustinian.factory.SingletonFactory;
import org.ustinian.loadbalancer.LoadBalancer;
import org.ustinian.loadbalancer.RandomLoadBalancer;
import org.ustinian.registry.NacosServiceDiscovery;
import org.ustinian.registry.ServiceDiscovery;
import org.ustinian.serializer.CommonSerializer;
import org.ustinian.transport.RpcClient;
import org.ustinian.entity.RpcRequest;
import org.ustinian.entity.RpcResponse;
import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

public class NettyClient implements RpcClient {
    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);
    private static final Bootstrap bootstrap;
    private static final EventLoopGroup group;

    static {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class);
//                .option(ChannelOption.SO_KEEPALIVE, true);
    }
    private static CommonSerializer serializer;
    private final UnprocessedRequests unprocessedRequests;
    private final ServiceDiscovery serviceDiscovery;

    public NettyClient() {
        this(DEFAULT_SERIALIZER, new RandomLoadBalancer());
    }
    public NettyClient(LoadBalancer loadBalancer) {
        this(DEFAULT_SERIALIZER, loadBalancer);
    }
    public NettyClient(Integer serializer) {
        this(serializer, new RandomLoadBalancer());
    }
    public NettyClient(Integer serializer, LoadBalancer loadBalancer) {
        this.serviceDiscovery = new NacosServiceDiscovery(loadBalancer);
        this.serializer = CommonSerializer.getByCode(serializer);
        this.unprocessedRequests = SingletonFactory.getInstance(UnprocessedRequests.class);
    }

    @Override
    public CompletableFuture<RpcResponse> sendRequest(RpcRequest rpcRequest) {
        if (serializer == null) {
            logger.error("未设置序列化器");
            throw new RpcException(RpcError.SERIALIZER_NOT_FOUND);
        }
        CompletableFuture<RpcResponse> resultFuture = new CompletableFuture<>();
        InetSocketAddress inetSocketAddress = serviceDiscovery.lookup(rpcRequest.getInterfaceName());
        Channel channel = ChannelProvider.get(inetSocketAddress, serializer);
        if (!channel.isActive()) {
            group.shutdownGracefully();
            return null;
        }
        unprocessedRequests.put(rpcRequest.getRequestId(), resultFuture);
        channel.writeAndFlush(rpcRequest).addListener((ChannelFutureListener) future1 -> {
            if (future1.isSuccess()) {
                logger.info(String.format("客户端发送消息: %s", rpcRequest.toString()));
            } else {
                future1.channel().close();
                resultFuture.completeExceptionally(future1.cause());
                logger.error("发送消息时有错误发生: ", future1.cause());
            }
        });
        return resultFuture;
    }
}
