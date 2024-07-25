package org.ustinian.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ustinian.util.NacosUtil;

import java.net.InetSocketAddress;

public class NacosServiceRegistry implements ServiceRegistry {
    private static final Logger logger = LoggerFactory.getLogger(NacosServiceRegistry.class);

    @Override
    public void register(String serviceName, InetSocketAddress inetSocketAddress) {
        NacosUtil.registerService(serviceName, inetSocketAddress);
    }
}
