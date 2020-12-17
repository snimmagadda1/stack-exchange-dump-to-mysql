package com.snimma1.config.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringDatasourceConfiguration {

    @Bean(name = "springDataSource")
    @Primary
    public DataSource springDataSource(
            SpringDatasourceConfigurationProperties springDatasourceConfigurationProperties) {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName(springDatasourceConfigurationProperties.getDriverClassName());
        datasource.setUrl(springDatasourceConfigurationProperties.getUrl());
        datasource.setUsername(springDatasourceConfigurationProperties.getUsername());
        datasource.setPassword(springDatasourceConfigurationProperties.getPassword());
        return datasource;
    }
}
