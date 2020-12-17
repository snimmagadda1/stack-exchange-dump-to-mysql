package com.snimma1.config.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class AppDatasourceConfiguration {

    @Bean(name = "appDataSource")
    @ConditionalOnProperty(name = "app.datasource.url")
    public DataSource appDataSource(
            AppDatasourceConfigurationProperties springDatasourceConfigurationProperties) {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName(springDatasourceConfigurationProperties.getDriverClassName());
        datasource.setUrl(springDatasourceConfigurationProperties.getUrl());
        datasource.setUsername(springDatasourceConfigurationProperties.getUsername());
        datasource.setPassword(springDatasourceConfigurationProperties.getPassword());
        return datasource;
    }

    @Bean(name = "appEntityManager")
    @PersistenceContext(unitName = "appPersU")
    public LocalContainerEntityManagerFactoryBean appEntityManagerFactory(
            @Qualifier("appDataSource") DataSource dataSource) {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("com.snimma1.model");
        factory.setPersistenceUnitName("appPersU");
        factory.setJpaVendorAdapter(adapter);
        return factory;
    }

    @Bean
    public JpaTransactionManager appJpaTransactionManager(
            @Qualifier("appEntityManager") EntityManagerFactory factory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factory);
        return transactionManager;
    }
}
