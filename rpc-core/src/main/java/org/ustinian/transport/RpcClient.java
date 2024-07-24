package org.ustinian.transport;

import org.ustinian.entity.RpcRequest;
import org.ustinian.serializer.CommonSerializer;

public interface RpcClient {
    Object sendRequest(RpcRequest rpcRequest);

    void setSerializer(CommonSerializer serializer);
}
