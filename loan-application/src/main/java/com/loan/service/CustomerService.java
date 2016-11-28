package com.loan.service;

import com.loan.domain.Admin;
import com.loan.domain.Customer;
import com.loan.request.CustomerResponse;

/**
 * Created by dogukan.ozturkan on 28.11.2016.
 */
public interface CustomerService {

    CustomerResponse authenticate(String emailAddress, String password);

    void saveCustomer(Customer customer);

    Customer findOne(long customerId);

}
