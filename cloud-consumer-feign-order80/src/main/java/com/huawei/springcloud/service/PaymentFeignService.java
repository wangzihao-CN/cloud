package com.huawei.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.huawei.springcloud.entities.CommonResult;
import com.huawei.springcloud.entities.Payment;

/**
 * @author WangZiHao
 * @date 2023/12/27
 */
@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @PostMapping(value = ("/payment/create"))
    CommonResult create(@RequestBody Payment payment);
    @GetMapping(value = ("/payment/get/{id}"))
    CommonResult getPaymentById(@PathVariable("id") Long id);
}
