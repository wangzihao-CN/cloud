package com.huawei.springcloud.lb.impl;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import com.huawei.springcloud.lb.LoadBalancer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WangZiHao
 * @date 2023/12/27
 */
@Component
public class Mylb implements LoadBalancer {
    private AtomicInteger atomicInteger;

    public Mylb() {
        atomicInteger = new AtomicInteger(0);
    }

    public final int incrementAndGetModulo(int modulo){
        int current;
        int next;
        while (true){
            current = atomicInteger.get();
            next = (current+1)%modulo;
            if (atomicInteger.compareAndSet(current,next)){
                System.out.println("第 "+ next +" 次访问");
                return next;
            }
        }
    }
    @Override
    public ServiceInstance getInstance(List<ServiceInstance> ServiceInstances) {
        int nextServerIndex = incrementAndGetModulo(ServiceInstances.size());
        return ServiceInstances.get(nextServerIndex);
    }
}
