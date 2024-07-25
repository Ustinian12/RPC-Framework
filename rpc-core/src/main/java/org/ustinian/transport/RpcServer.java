package org.ustinian.transport;

import org.ustinian.serializer.CommonSerializer;

public interface RpcServer {
    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;
    void start();
    <T> void publishService(Object service, Class<T> serviceClass);
}
