package com.huawei.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author WangZiHao
 * @date 2023/12/27
 */
public interface LoadBalancer {
    ServiceInstance getInstance(List<ServiceInstance> ServiceInstances);
}
