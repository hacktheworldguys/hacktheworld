package com.loan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.EqualsAndHashCode;

@Entity
@Table(indexes = {
        @Index(name = "idx_first_name_last_name", columnList = "firstName,lastName"),
        @Index(name = "idx_email", columnList = "email")})
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {

    private static final long serialVersionUID = 4510680420289546192L;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType status = StatusType.PASSIVE;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date birthday;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = false)
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(nullable = true)
    private String mobile;

    @JsonIgnore
    @Column(nullable = true)
    private String address;

    @JsonIgnore
    @Column(nullable = true)
    private BigDecimal annualSalary;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType = CustomerType.STANDARD;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "customer", targetEntity = Loan.class, orphanRemoval = true)
    private List<Loan> loan;

    @JsonIgnore
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    @JsonIgnore
    public boolean hasMobile() {
        return !StringUtils.isEmpty(this.getMobile());
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public List<Loan> getLoan() {
        return loan;
    }

    public void setLoan(List<Loan> loan) {
        this.loan = loan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(BigDecimal annualSalary) {
        this.annualSalary = annualSalary;
    }

    public enum StatusType {
        ACTIVE, PASSIVE, LOCKED
    }

    public enum CustomerType{
        STANDARD,
        BUSINESS;
    }
}
