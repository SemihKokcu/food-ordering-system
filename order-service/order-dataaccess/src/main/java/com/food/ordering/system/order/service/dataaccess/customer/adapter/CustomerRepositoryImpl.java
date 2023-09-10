package com.food.ordering.system.order.service.dataaccess.customer.adapter;

import com.food.ordering.system.order.service.dataaccess.customer.mapper.CustomerDataAccessMapper;
import com.food.ordering.system.order.service.dataaccess.customer.repository.CustomerJpaRespository;
import com.food.ordering.system.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {
    private  final CustomerJpaRespository customerJpaRespository;
    private  final CustomerDataAccessMapper customerDataAccessMapper;

    public CustomerRepositoryImpl(CustomerJpaRespository customerJpaRespository, CustomerDataAccessMapper customerDataAccessMapper) {
        this.customerJpaRespository = customerJpaRespository;
        this.customerDataAccessMapper = customerDataAccessMapper;
    }

    @Override
    public Optional<Customer> findCustomer(UUID customerId) {
        return  customerJpaRespository.findById(customerId).map(customerDataAccessMapper::customerEntitytoCustomer);
    }
}
