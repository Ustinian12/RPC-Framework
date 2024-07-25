package org.ustinian.transport;

import org.ustinian.entity.RpcRequest;
import org.ustinian.serializer.CommonSerializer;

public interface RpcClient {
    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    Object sendRequest(RpcRequest rpcRequest);
}
