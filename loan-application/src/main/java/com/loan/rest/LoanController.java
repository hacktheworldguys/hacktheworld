package com.loan.rest;

import com.loan.domain.Customer;
import com.loan.repository.CustomerRepository;
import com.loan.request.CustomerRequest;
import com.loan.service.CustomerService;
import com.loan.service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/api/loan")
@Component
public class LoanController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LoanService loanService;

    @POST
    @Path("/{customerId}/apply")
    public void apply(@PathParam("customerId") long customerId, @QueryParam("amount") BigDecimal amount, @QueryParam("paymentTerm") int paymentTerm) {

        Customer customer = customerService.findOne(customerId);

        if(customer==null)
            throw new IllegalArgumentException("no suitable customer found.");

        loanService.saveApply(amount,paymentTerm,customer);
    }

}
