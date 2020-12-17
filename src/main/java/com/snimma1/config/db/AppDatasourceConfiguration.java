package com.snimma1.config.db;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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
}
