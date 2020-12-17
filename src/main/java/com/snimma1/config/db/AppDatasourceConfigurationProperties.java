package com.snimma1.config.db;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.datasource")
@Data
public class AppDatasourceConfigurationProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
