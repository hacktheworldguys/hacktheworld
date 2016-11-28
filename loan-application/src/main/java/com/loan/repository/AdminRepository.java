package com.loan.repository;

import com.loan.domain.Admin;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends PagingAndSortingRepository<Admin, Long>,
        JpaSpecificationExecutor<Admin>, QueryDslPredicateExecutor<Admin> {

    int countByEmailAndPassword(String email, String password);
}
