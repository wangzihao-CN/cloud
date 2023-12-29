package com.huawei.springcloud.service.impl;

import org.springframework.stereotype.Component;
import com.huawei.springcloud.service.PaymentFeignHystrixService;

/**
 * @author WangZiHao
 * @date 2023/12/28
 */
@Component
public class PaymentFallback implements PaymentFeignHystrixService {
    @Override
    public String getPaymentById(Integer id) {
        return "解耦fallback方法——getPaymentById " + id;
    }

    @Override
    public String payment_timeout(Integer id) {
        return "解耦fallback方法——payment_timeout " + id;
    }
}
