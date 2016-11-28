package com.loan.repository;


import com.loan.domain.Customer;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>,
        JpaSpecificationExecutor<Customer>, QueryDslPredicateExecutor<Customer> {

    Customer findByEmail(String email);

    List<Customer> findByStatus(Customer.StatusType status);

    List<Customer> findByStatusAndCreatedDateAfter(Customer.StatusType status, DateTime date);

    Customer findByMobile(String mobileCountryCode, String mobile);

    @Query("select c.customerType from Customer c where c.id = :customerId")
    Customer.CustomerType findCustomerTypeById(@Param("customerId") Long customerId);

    Customer findByEmailAndPassword(String email, String password);
}
