package org.ustinian;

import org.ustinian.entity.RpcRequest;

public interface RpcClient {
    Object sendRequest(RpcRequest rpcRequest);
}
