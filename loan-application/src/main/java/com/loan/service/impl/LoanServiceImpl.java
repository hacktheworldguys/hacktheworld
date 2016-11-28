package com.loan.service.impl;

import com.loan.domain.Admin;
import com.loan.domain.Customer;
import com.loan.domain.Loan;
import com.loan.repository.AdminRepository;
import com.loan.repository.LoanRepository;
import com.loan.service.AdminService;
import com.loan.service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by dogukan.ozturkan on 28.11.2016.
 */

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    @Transactional
    public void saveApply(BigDecimal amount, int term, Customer customer) {

        Loan loan = new Loan();
        loan.setAmount(amount);
        loan.setTerm(term);
        loan.setCustomer(customer);

        loanRepository.save(loan);
    }
}
