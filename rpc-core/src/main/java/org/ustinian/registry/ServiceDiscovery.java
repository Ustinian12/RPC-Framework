package org.ustinian.registry;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.net.InetSocketAddress;

/**
 * @Description
 * @Author Ustinian12
 * @Date 2024/7/24
 */
public interface ServiceDiscovery {
    /**
    * @description:
    * @param serviceName
    * @return java.net.InetSocketAddress
    * @author Ustinian12
    * @date 2024/7/24
    */
    InetSocketAddress lookup(String serviceName);
}
