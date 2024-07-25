package org.ustinian;

import org.ustinian.annotation.ServiceScan;
import org.ustinian.serializer.CommonSerializer;
import org.ustinian.serializer.ProtobufSerializer;
import org.ustinian.transport.RpcServer;
import org.ustinian.transport.netty.server.NettyServer;
import org.ustinian.provider.ServiceProviderImpl;
import org.ustinian.provider.ServiceProvider;
@ServiceScan
public class NettyTestServer{
    public static void main(String[] args){
        RpcServer server = new NettyServer("127.0.0.1", 9999, CommonSerializer.PROTOBUF_SERIALIZER);
        server.start();
    }
}
