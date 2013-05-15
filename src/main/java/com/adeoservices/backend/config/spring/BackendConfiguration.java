package com.adeoservices.backend.config.spring;

import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Properties;


@Configuration
@EnableJpaRepositories("com.adeoservices.backend.repository")
@ComponentScan(basePackages = {"com.adeoservices.backend.controller"})
@EnableTransactionManagement
@EnableCaching
public class BackendConfiguration {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource protected DataSource dataSource;

    @Resource
    protected Environment env;


    /**
     * DB initialization
     * @return
     */
    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase b = new SpringLiquibase();
        b.setDataSource(dataSource);
        b.setChangeLog("classpath:liquibase/changelog.xml");
        b.setContexts("test");
        return b;
    }


    @Bean
    public JpaTransactionManager transactionManager() throws ClassNotFoundException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    @DependsOn("liquibase")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ClassNotFoundException {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("com.adeoservices.backend.domain");
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);

        Properties jpaProterties = new Properties();
        jpaProterties.put("hibernate.dialect", env.getRequiredProperty("hibernate-dialect"));
        jpaProterties.put("hibernate.show_sql", env.getRequiredProperty("hibernate-showsql"));

        entityManagerFactoryBean.setJpaProperties(jpaProterties);

        return entityManagerFactoryBean;
    }

    @Bean
    public CacheManager cacheManager() {
        // configure and return an implementation of Spring's CacheManager SPI
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("default")));
        return cacheManager;
    }

}
