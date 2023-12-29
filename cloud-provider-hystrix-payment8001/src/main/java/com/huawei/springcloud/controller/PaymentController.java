package com.huawei.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.huawei.springcloud.service.PaymentService;

/**
 * @author WangZiHao
 * @date 2023/12/27
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String servicePort;
    @GetMapping(value = ("/payment/get/{id}"))
    public String getPaymentById(@PathVariable("id") Integer id){
        String result = paymentService.payment_ok(id);
        log.info("result---"+result);
        return result;
    }
    @GetMapping(value = ("/payment/timeout/{id}"))
    public String payment_timeout(@PathVariable("id") Integer id){
        String result = paymentService.payment_timeout(id);
        log.info("result---"+result);
        return result;
    }
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("*********result: " + result);
        return result;
    }
}
