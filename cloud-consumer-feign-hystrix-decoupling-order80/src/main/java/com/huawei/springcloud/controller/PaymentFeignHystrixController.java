package com.huawei.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.huawei.springcloud.service.PaymentFeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author WangZiHao
 * @date 2023/12/28
 */
@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "defaultFallbackMethod")
public class PaymentFeignHystrixController {
    @Resource
    private PaymentFeignHystrixService paymentFeignHystrixService;
    @GetMapping(value = "/consumer/payment/hystrix/get/{id}")
    public String getPaymentById(@PathVariable("id") Integer id){
//        String s = paymentFeignHystrixService.payment_timeout(id);
        String s = paymentFeignHystrixService.getPaymentById(id);
        log.info("result  "+s);
        return s;
    }
//    @HystrixCommand(fallbackMethod = "fallbackMethod")
//    @HystrixCommand
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    public String payment_timeout(@PathVariable("id") Integer id){
//        int d = 1/0;
        String s = paymentFeignHystrixService.payment_timeout(id);
        log.info("result  "+s);
        return s;
    }
    public String fallbackMethod(@PathVariable("id") Integer id){
        return "falllback " + id;
    }
    public String defaultFallbackMethod(){
        return "falllback globle";
    }
}
