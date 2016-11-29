package com.loan.rest;

import com.loan.domain.Customer;
import com.loan.domain.Loan;
import com.loan.request.LoanRequest;
import com.loan.service.CustomerService;
import com.loan.service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/api/loan")
@Component
public class LoanController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LoanService loanService;

    @POST
    @Path("/apply")
    public void apply(LoanRequest loanRequest) {

        Customer customer = customerService.findOne(loanRequest.getCustomerId());

        if (customer == null)
            throw new IllegalArgumentException("no suitable customer found.");

        List<Loan> loans = loanService.findByIpAddressAndCustomerId(loanRequest.getIpAddress(),loanRequest.getCustomerId());

        loanService.saveApply(loanRequest, customer);
    }


    @POST
    @Path("/applies")
    @Produces("application/json")
    public List<Loan> applyList(LoanRequest loanRequest) {

        Customer customer = customerService.findOne(loanRequest.getCustomerId());

        if (customer == null)
            throw new IllegalArgumentException("no suitable customer found.");

        List<Loan> loans = loanService.getLoanApplyByCustomerId(customer.getId());

        return loans;
    }

}
