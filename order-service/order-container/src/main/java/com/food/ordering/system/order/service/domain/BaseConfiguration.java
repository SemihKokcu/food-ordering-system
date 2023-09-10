package com.food.ordering.system.order.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConfiguration {

    //domain de spring başlatıcısı ya da bir notasyon eklemedik burada bean olarak beliritoyurz
    @Bean
    public OrderDomainService orderDomainService(){
        return  new OrderDomainServiceImpl();
    }
}
