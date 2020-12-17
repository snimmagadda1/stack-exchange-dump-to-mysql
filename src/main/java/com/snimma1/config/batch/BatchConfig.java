package com.snimma1.config.batch;

import com.snimma1.config.readers.ReadersConfig;
import com.snimma1.config.writers.WritersConfig;
import com.snimma1.model.Post;
import com.snimma1.processor.PostProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

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

    @Bean
    PostProcessor postProcessor() {
        return new PostProcessor();
    }

    /**
     * Job pipeline
     *
     * @return Job - configured job
     */
    @Bean
    public Job importUserJob(@Qualifier("step1") Step step1) {
        return jobBuilderFactory
                .get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }
    /**
     * Job Step
     *
     * @return Step current configured step
     */
    @Bean
    public Step step1(
            @Qualifier("appJpaTransactionManager") JpaTransactionManager transactionManager,
            @Qualifier("appEntityManager") LocalContainerEntityManagerFactoryBean factory,
            PostProcessor postProcessor) {
        return stepBuilderFactory
                .get("step1")
                .transactionManager(transactionManager)
                .<Post, Post>chunk(100)
                .reader(readers.postsReader())
                .processor(postProcessor)
                .writer(writers.jpaItemWriter(factory))
                .faultTolerant()
                .build();
    }
}
