package org.ustinian.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * @Description
 * @Author Ustinian12
 * @Date 2024/7/24
 */
public class RoundRobinLoadBalancer implements LoadBalancer {
    private int index = 0;
    @Override
    public Instance select(List<Instance> instances) {
        if(index >= instances.size()) {
            index %= instances.size();
        }
        return instances.get(index);
    }
}
