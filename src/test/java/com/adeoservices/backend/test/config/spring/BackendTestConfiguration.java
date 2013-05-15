package com.adeoservices.backend.test.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: bgr
 * Date: 15/05/13
 * Time: 11:37
 */

@Configuration
@ComponentScan(basePackages = {"com.adeoservices.backend.controller"})
public class BackendTestConfiguration {
}
