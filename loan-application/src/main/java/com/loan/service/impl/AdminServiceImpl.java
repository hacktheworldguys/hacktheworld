package com.loan.service.impl;

import com.loan.domain.Admin;
import com.loan.repository.AdminRepository;
import com.loan.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dogukan.ozturkan on 28.11.2016.
 */

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void saveAdmin(Admin admin) {

        adminRepository.save(admin);
    }

    @Override
    public String authenticate(String emailAddress, String password) {
        int count = adminRepository.countByEmailAndPassword(emailAddress, password);

        if (count > 0)
            return "OK";
        else
            return "NOK";
    }
}
