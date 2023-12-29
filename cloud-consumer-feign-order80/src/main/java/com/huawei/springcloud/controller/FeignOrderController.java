package com.huawei.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.huawei.springcloud.entities.CommonResult;
import com.huawei.springcloud.entities.Payment;
import com.huawei.springcloud.service.PaymentFeignService;

/**
 * @author WangZiHao
 * @date 2023/12/27
 */
@RestController
@Slf4j
public class FeignOrderController {
    @Autowired
    private PaymentFeignService paymentFeignService;
    @GetMapping(value = ("/consumer/payment/create"))
//    public int create(@RequestBody Payment payment){
    public int create(Payment payment){
        CommonResult result = paymentFeignService.create(payment);
        return 0;
    }

    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }
}
