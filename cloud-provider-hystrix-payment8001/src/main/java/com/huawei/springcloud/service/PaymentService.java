package com.huawei.springcloud.service;

import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import java.util.concurrent.TimeUnit;

/**
 * @author WangZiHao
 * @date 2023/12/27
 */
@Service
public class PaymentService {
    public String payment_ok(Integer id){
        return "线程池: "+ Thread.currentThread().getName()+"payment_ok,id: "+id;
    }
    @HystrixCommand(fallbackMethod = "timeout_fallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String payment_timeout(Integer id){
        int timecount = 10;
        int j = 1/0;
        /*try {
            TimeUnit.SECONDS.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
        return "线程池: "+ Thread.currentThread().getName()+"payment_timeout,id: "+id
                +"\t" +"哈哈: 耗时(秒) "+timecount;
    }
    public String timeout_fallback(Integer id){
        return "线程池: "+ Thread.currentThread().getName()+"payment_timeout,id: "+id
                +"\t" +"系统繁忙，请稍后重试";
    }
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties ={
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    }) // 在10s内10次请求有60%失败 // 请求次数要先满足，再看百分比
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("*****id 不为负数");
        }
        /**
         * huTool工具包的方法
         * 在自定义的cloud-api-commons里引入了
         */
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号： " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id不能为负数，请稍候再试，/(T o T)/~~ id: " + id;
    }
}
