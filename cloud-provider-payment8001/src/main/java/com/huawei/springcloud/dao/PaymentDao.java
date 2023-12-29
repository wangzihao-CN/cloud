package com.huawei.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.huawei.springcloud.entities.Payment;

/**
 * @author WangZiHao
 * @date 2023/12/26
 */
@Mapper
public interface PaymentDao {
    int create(Payment payment);
    Payment getPaymentById(@Param("id") Long id);
}
