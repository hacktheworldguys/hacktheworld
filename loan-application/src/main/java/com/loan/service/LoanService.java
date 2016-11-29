package com.loan.service;

import com.loan.domain.Customer;
import com.loan.domain.Loan;
import com.loan.request.LoanRequest;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dogukan.ozturkan on 28.11.2016.
 */
public interface LoanService {

        void saveApply(LoanRequest loanRequest, Customer customer);

        List<Loan> getLoanApplyByCustomerId(long customerId);

        List<Loan> findByIpAddressAndCustomerId(String ipAddress, long customerId);
}
