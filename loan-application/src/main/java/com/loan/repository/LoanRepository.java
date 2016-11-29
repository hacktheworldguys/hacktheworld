package com.loan.repository;

import com.loan.domain.Loan;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends PagingAndSortingRepository<Loan, Long>,
        JpaSpecificationExecutor<Loan>, QueryDslPredicateExecutor<Loan> {

    List<Loan> findByCustomerId(long customerId);

    List<Loan> findByIpAddressAndCustomerId(String ipAddress, long customerId);
}
