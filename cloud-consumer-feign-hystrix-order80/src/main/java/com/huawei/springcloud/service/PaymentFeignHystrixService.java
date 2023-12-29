package com.huawei.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author WangZiHao
 * @date 2023/12/27
 */
@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignHystrixService {
    @GetMapping(value = ("/payment/get/{id}"))
    public String getPaymentById(@PathVariable("id") Integer id);
    @GetMapping(value = ("/payment/timeout/{id}"))
    public String payment_timeout(@PathVariable("id") Integer id);
}
