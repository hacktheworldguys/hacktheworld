package com.loan.service.impl;

import com.loan.domain.Admin;
import com.loan.repository.AdminRepository;
import com.loan.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dogukan.ozturkan on 28.11.2016.
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void saveAdmin(Admin admin) {

        adminRepository.save(admin);
    }
}
