package com.snimma1.config.batch;

import com.snimma1.config.readers.ReadersConfig;
import com.snimma1.config.writers.WritersConfig;
import com.snimma1.model.Post;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author snimmagadda1 Configuration class for batch jobs */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired public JobBuilderFactory jobBuilderFactory;

    @Autowired public StepBuilderFactory stepBuilderFactory;

    /** Holds all readers available for job */
    @Autowired private ReadersConfig readers;

    /** Holds all writers available for job */
    @Autowired private WritersConfig writers;

    /**
     * Job pipeline
     *
     * @return Job - configured job
     */
    @Bean
    public Job importUserJob() {
        return jobBuilderFactory
                .get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }
    /**
     * Job Step
     *
     * @return Step current configured step
     */
    @Bean
    public Step step1() {
        return stepBuilderFactory
                .get("step1")
                .<Post, Post>chunk(10)
                .reader(readers.postsReader())
                //                .processor(processor())
                .writer(writers.consoleItemWriter())
                .build();
    }
}
