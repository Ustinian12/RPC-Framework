package org.ustinian.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * @Description
 * @Author Ustinian12
 * @Date 2024/7/24
 */
public interface LoadBalancer {
    Instance select(List<Instance> instances);
}
