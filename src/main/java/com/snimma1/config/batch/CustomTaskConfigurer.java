package com.snimma1.config.batch;

import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;

import javax.sql.DataSource;

public class CustomTaskConfigurer extends DefaultTaskConfigurer {

    public CustomTaskConfigurer(DataSource dataSource) {
        super(dataSource);
    }
}
