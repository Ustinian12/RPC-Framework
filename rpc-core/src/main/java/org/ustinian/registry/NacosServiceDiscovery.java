package org.ustinian.registry;


import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ustinian.loadbalancer.LoadBalancer;
import org.ustinian.loadbalancer.RandomLoadBalancer;
import org.ustinian.util.NacosUtil;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @Description
 * @Author Ustinian12
 * @Date 2024/7/24
 */
public class NacosServiceDiscovery implements ServiceDiscovery {
    private static final Logger logger = LoggerFactory.getLogger(NacosServiceDiscovery.class);
    private final LoadBalancer loadBalancer;

    public NacosServiceDiscovery(LoadBalancer loadBalancer) {
        if(loadBalancer == null) this.loadBalancer = new RandomLoadBalancer();
        else this.loadBalancer = loadBalancer;
    }

    @Override
    public InetSocketAddress lookup(String serviceName) {
        try {
            List<Instance> instances = NacosUtil.getAllInstances(serviceName);
            Instance instance = loadBalancer.select(instances);
            return new InetSocketAddress(instance.getIp(), instance.getPort());
        } catch (NacosException e) {
            logger.error("获取服务时有错误发生:", e);
        }
        return null;
    }

}
