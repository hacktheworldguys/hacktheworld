package com.loan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.loan.util.DateUtil;
import com.loan.util.LocalDateConverter;

import org.springframework.util.StringUtils;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(indexes = {
        @Index(name = "idx_first_name_last_name", columnList = "firstName,lastName"),
        @Index(name = "idx_email_password", columnList = "email, password")})
@EqualsAndHashCode(callSuper = true)
@Data
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
    @Convert(converter = LocalDateConverter.class)
    private LocalDate birthday;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(nullable = true)
    private String mobile;

    @JsonIgnore
    @Column(nullable = true)
    private String password;

    @JsonIgnore
    @Column(nullable = true)
    private String address;

    @Enumerated(EnumType.STRING)
    private CreditStatus creditStatus;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType = CustomerType.STANDARD;


    @JsonIgnore
    public long getCustomerAge() {
        return DateUtil.getAge(this.getBirthday());
    }

    @JsonIgnore
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    @JsonIgnore
    public boolean hasMobile() {
        return !StringUtils.isEmpty(this.getMobile());
    }


    public enum StatusType {
        ACTIVE, PASSIVE, LOCKED
    }


    public enum CreditStatus {
        INITIALIZED,
        APPROVED,
        SUSPENDED;

        public boolean isApproved() {
            return APPROVED == this;
        }

        public boolean shouldDashboardVisible() {
            return APPROVED == this || SUSPENDED == this;
        }
    }

    public enum CustomerType{
        STANDARD,
        BUSINESS;
    }
}
