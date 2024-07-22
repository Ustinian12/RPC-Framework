package org.ustinian.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ustinian.enumeration.RpcError;
import org.ustinian.exception.RpcException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultServiceRegistry implements ServiceRegistry {
    private static final Logger logger = LoggerFactory.getLogger(DefaultServiceRegistry.class);
    private final Map<String, Object> serviceMap = new ConcurrentHashMap<>();
    private final Set<String> registeredServices = ConcurrentHashMap.newKeySet();

    @Override
    public <T> void registerService(T service) {
        String serviceName = service.getClass().getCanonicalName();
        if (registeredServices.contains(serviceName)) return;
        registeredServices.add(serviceName);
        Class<?>[] interfaces = service.getClass().getInterfaces();
        if(interfaces.length == 0) {
            throw new RpcException(RpcError.SERVICE_NOT_IMPLEMENT_ANY_INTERFACE);
        }
        for (Class<?> i : interfaces) {
            serviceMap.put(i.getCanonicalName(), service);
        }
        logger.info("向接口: {} 注册服务: {}", interfaces, serviceName);
    }

    @Override
    public Object getService(String serviceName) {
        Object service = serviceMap.get(serviceName);
        if(service == null) {
            throw new RpcException(RpcError.SERVICE_NOT_FOUND);
        }
        return service;
    }
}