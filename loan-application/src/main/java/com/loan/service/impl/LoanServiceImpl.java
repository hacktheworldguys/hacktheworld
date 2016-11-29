package com.loan.service.impl;

import com.loan.domain.Customer;
import com.loan.domain.Loan;
import com.loan.repository.LoanRepository;
import com.loan.request.LoanRequest;
import com.loan.service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

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
    public void saveApply(LoanRequest loanRequest, Customer customer) {

        Loan loan = new Loan();
        loan.setAmount(loanRequest.getAmount());
        loan.setTerm(loanRequest.getTerm());

        BigDecimal customerMonthlySalary = customer.getAnnualSalary().divide(BigDecimal.valueOf(24.0), 3, RoundingMode.CEILING);
        BigDecimal customerMonthlyPayment = loanRequest.getAmount().divide(BigDecimal.valueOf(loanRequest.getTerm()), 3, BigDecimal.ROUND_CEILING);

        if (customerMonthlySalary.compareTo(customerMonthlyPayment) > 0) {
            loan.setCreditStatus(Loan.CreditStatus.APPROVED);
        } else {
            loan.setCreditStatus(Loan.CreditStatus.REJECTED);
        }

        loan.setIpAddress(loanRequest.getIpAddress());
        loan.setCustomer(customer);

        loanRepository.save(loan);
    }

    @Override
    public List<Loan> getLoanApplyByCustomerId(long customerId) {
        return loanRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Loan> findByIpAddressAndCustomerId(String ipAddress, long customerId) {
        return loanRepository.findByIpAddressAndCustomerId(ipAddress, customerId);
    }
}
