package com.loan.service;

import com.loan.domain.Customer;

import java.math.BigDecimal;

/**
 * Created by dogukan.ozturkan on 28.11.2016.
 */
public interface LoanService {

        void saveApply(BigDecimal amount, int term,Customer customer);
}
