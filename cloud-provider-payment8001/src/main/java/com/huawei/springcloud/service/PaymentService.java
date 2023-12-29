package com.huawei.springcloud.service;

import com.huawei.springcloud.entities.Payment;

/**
 * @author WangZiHao
 * @date 2023/12/26
 */
public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(Long id);
}
