package com.snimma1.config.db;

import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class AppDatasourceConfiguration {

    @Bean(name = "appDataSource")
    @ConditionalOnProperty(name = "app.datasource.url")
    public DataSource appDataSource(
            AppDatasourceConfigurationProperties appDatasourceConfigurationProperties) {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName(appDatasourceConfigurationProperties.getDriverClassName());
        datasource.setUrl(appDatasourceConfigurationProperties.getUrl());
        datasource.setUsername(appDatasourceConfigurationProperties.getUsername());
        datasource.setPassword(appDatasourceConfigurationProperties.getPassword());
        return datasource;
    }

    @Bean(name = "appEntityManager")
    @PersistenceContext(unitName = "appPersU")
    public LocalContainerEntityManagerFactoryBean appEntityManagerFactory(
            AppDatasourceConfigurationProperties appDatasourceConfigurationProperties,
            @Qualifier("appDataSource") DataSource dataSource) {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", appDatasourceConfigurationProperties.getDialect());
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setJpaDialect(new HibernateJpaDialect());
        factory.setPackagesToScan("com.snimma1.model");
        factory.setPersistenceUnitName("appPersU");
        factory.setJpaVendorAdapter(adapter);
        factory.setJpaProperties(properties);
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
