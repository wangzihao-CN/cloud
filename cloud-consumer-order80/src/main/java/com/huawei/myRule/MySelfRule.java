package com.huawei.myRule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * @author WangZiHao
 * @date 2023/12/27
 */
@Configuration
public class MySelfRule {
   /* @Bean
    public IRule myRule(){
        return new RandomRule();
    }*/
    @Bean
    public IRule myRule(){
        return new MyIRule();
    }
}
