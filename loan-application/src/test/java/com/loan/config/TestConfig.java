package com.loan.config;

import com.loan.rest.RestResource;
import com.loan.service.MessageService;
import com.loan.service.impl.CustomerServiceImpl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.loan"})
public class TestConfig {
}
