package com.adeoservices.backend.test.config.spring;

import com.adeoservices.backend.config.spring.BackendConfiguration;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: bgr
 * Date: 15/05/13
 * Time: 11:37
 */

@Configuration
@Import(BackendConfiguration.class)
@PropertySource({"classpath:conf/db/backend-ds.properties"})
public class BackendTestConfiguration {

    @Resource
    protected Environment env;

    @Bean
    DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUsername(env.getRequiredProperty("dataSource-username"));
        dataSource.setPassword(env.getRequiredProperty("dataSource-password"));
        dataSource.setDriverClassName(env.getRequiredProperty("dataSource-driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("dataSource-url"));

        return dataSource;
    }

}
