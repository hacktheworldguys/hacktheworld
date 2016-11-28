package com.loan.service.impl;

import com.loan.domain.Customer;
import com.loan.repository.CustomerRepository;
import com.loan.request.CustomerResponse;
import com.loan.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dogukan.ozturkan on 28.11.2016.
 */

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerResponse authenticate(String emailAddress, String password) {
        Customer customer = customerRepository.findByEmailAndPassword(emailAddress, password);
        CustomerResponse customerResponse = new CustomerResponse();
        if (customer != null) {
            customerResponse.setCustomerId(customer.getId());
            customerResponse.setEmail(customer.getEmail());
            customerResponse.setMessage("OK");
        }else{
            customerResponse.setMessage("NOK");
        }

        return  customerResponse;
    }

    @Override
    public void saveCustomer(Customer customer) {

        customerRepository.save(customer);
    }

    @Override
    public Customer findOne(long customerId) {
        return customerRepository.findOne(customerId);
    }
}
