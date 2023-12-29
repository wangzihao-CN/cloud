package com.huawei.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.huawei.springcloud.service.impl.PaymentFallback;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author WangZiHao
 * @date 2023/12/28
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",fallback = PaymentFallback.class)
//当hystrix没有在yml文件通过Feign开启的时候(通过feign开启表示两者融合，至于怎么融合的我们不用去在意，直接使用就行)，
// FeignClient里有关hystrix的内容是无效的，比如fallback
public interface PaymentFeignHystrixService {
    @GetMapping(value = ("/payment/get/{id}"))
    public String getPaymentById(@PathVariable("id") Integer id);
    @GetMapping(value = ("/payment/timeout/{id}"))
    public String payment_timeout(@PathVariable("id") Integer id);
}
