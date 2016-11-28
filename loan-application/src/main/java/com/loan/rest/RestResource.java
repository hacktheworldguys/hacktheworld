package com.loan.rest;

import com.loan.domain.Admin;
import com.loan.domain.Customer;
import com.loan.domain.Message;
import com.loan.request.AdminRequest;
import com.loan.request.CustomerRequest;
import com.loan.service.AdminService;
import com.loan.service.CustomerService;
import com.loan.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/loan")
@Component
public class RestResource {

    @Autowired
    private MessageService messageService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminService adminService;

    @POST
    @Path("/customer")
    public void saveCustomer(CustomerRequest customerRequest) {
        Customer customer = getCustomerModel(customerRequest);
        customerService.saveCustomer(customer);
    }

    @POST
    @Path("/admin")
    public void saveAdmin(AdminRequest adminRequest) {
        Admin admin= getAdminModel(adminRequest);
        adminService.saveAdmin(admin);
    }

    @POST
    @Path("/login")
    public String authenticate(AdminRequest adminRequest) {
        return adminService.authenticate(adminRequest.getEmailAddress(), adminRequest.getPassword());
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/messages")
    public List<Message> message() {
        return messageService.getMessages();
    }

    private Customer getCustomerModel(CustomerRequest customerRequest) {

        Customer customer = new Customer();
        customer.setCustomerType(Customer.CustomerType.STANDARD);
        customer.setAddress(customerRequest.getAddress());
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setEmail(customerRequest.getEmailAddress());

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = null;
        try {
            dt = formatter.parse(customerRequest.getBirthday());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        customer.setBirthday(dt);
        customer.setMobile(customerRequest.getMobilePhone());
        customer.setCreditStatus(Customer.CreditStatus.INITIALIZED);
        customer.setStatus(Customer.StatusType.ACTIVE);

        return customer;
    }

    private Admin getAdminModel(AdminRequest adminRequest) {
        Admin admin = new Admin();
        admin.setFirstName(adminRequest.getFirstName());
        admin.setLastName(adminRequest.getLastName());
        admin.setEmail(adminRequest.getEmailAddress());
        admin.setPassword(adminRequest.getPassword());

        return admin;
    }
}
