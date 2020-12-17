package com.snimma1.config.batch;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.cloud.task.configuration.TaskConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfigurerConfig {

    /**
     * Configure default batch datasource
     *
     * @param dataSource
     * @return
     */
    @Bean
    BatchConfigurer batchConfigurer(@Qualifier("springDataSource") DataSource dataSource) {
        return new DefaultBatchConfigurer(dataSource);
    }

    /**
     * Configure default task datasource
     *
     * @param dataSource
     * @return
     */
    @Bean
    TaskConfigurer taskConfigurer(@Qualifier("springDataSource") DataSource dataSource) {
        return new DefaultTaskConfigurer(dataSource);
    }
}
