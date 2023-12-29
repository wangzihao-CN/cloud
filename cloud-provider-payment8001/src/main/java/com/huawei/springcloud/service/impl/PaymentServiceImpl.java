package com.huawei.springcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huawei.springcloud.dao.PaymentDao;
import com.huawei.springcloud.entities.Payment;
import com.huawei.springcloud.service.PaymentService;

import javax.annotation.Resource;

/**
 * @author WangZiHao
 * @date 2023/12/26
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
