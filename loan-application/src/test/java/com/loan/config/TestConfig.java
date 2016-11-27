package com.loan.config;

import com.loan.rest.RestResource;
import com.loan.service.MessageService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Date: 31/01/2014
 * Time: 00:41
 *
 * @author Geoffroy Warin (http://geowarin.github.io)
 */
@Configuration
@ComponentScan(basePackageClasses = {RestResource.class, MessageService.class})
public class TestConfig {
}
