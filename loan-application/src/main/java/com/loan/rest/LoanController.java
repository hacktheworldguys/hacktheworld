package com.loan.rest;

import com.loan.domain.Customer;
import com.loan.domain.Loan;
import com.loan.request.LoanRequest;
import com.loan.service.CustomerService;
import com.loan.service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public String apply(LoanRequest loanRequest) {

        Customer customer = customerService.findOne(loanRequest.getCustomerId());

        if (customer == null)
            throw new IllegalArgumentException("no suitable customer found.");

        List<Loan> loans = loanService.findByIpAddressAndCustomerId(loanRequest.getIpAddress(), loanRequest.getCustomerId());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int applyLimit = 0;
        for (Loan loan : loans) {
            try {

                Date systemDate = dateFormat.parse(dateFormat.format(new Date()));
                Date createdDate = dateFormat.parse(loan.getCreatedDate().toString());

                if (systemDate.compareTo(createdDate) == 0) {
                    applyLimit++;
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (applyLimit < 3) {

            loanService.saveApply(loanRequest, customer);

            return "OK";

        } else {
            return "You exceed maximum limit of apply at same day";
        }
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
