package org.ustinian.registry;

public interface ServiceRegistry {
    <T> void registerService(T service);
    Object getService(String serviceName);
}
