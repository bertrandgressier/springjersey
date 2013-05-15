package com.adeoservices.backend.config.spring;

import org.springframework.context.annotation.ImportResource;

/**
 * No activated ==> It's not the target. Oauth configuration needed
 */
//@Configuration
@ImportResource("classpath:conf/security/applicationContext-security.xml")
public class BackendSecurityConfiguration {
	

}
