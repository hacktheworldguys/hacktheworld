package com.loan.service.impl;

import com.loan.domain.Customer;
import com.loan.repository.CustomerRepository;
import com.loan.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dogukan.ozturkan on 28.11.2016.
 */

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void saveCustomer(Customer customer) {

        customerRepository.save(customer);
    }
}
