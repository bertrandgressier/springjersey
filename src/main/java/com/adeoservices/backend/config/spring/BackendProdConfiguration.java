package com.adeoservices.backend.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: bgr
 * Date: 13/05/13
 * Time: 15:39
 */

@Configuration
@Profile("prod")
@PropertySource({"classpath:conf/db/backend-ds.properties"})
public class BackendProdConfiguration {


    @Resource
    protected Environment env;

    @Bean
    DataSource dataSource() {
        try {
            Context ctx = new InitialContext();
            return (DataSource) ctx.lookup(env.getRequiredProperty("dataSource-jndi"));
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
