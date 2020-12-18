package com.snimma1.config.batch;

import com.snimma1.config.readers.ReadersConfig;
import com.snimma1.config.writers.WritersConfig;
import com.snimma1.model.Comment;
import com.snimma1.model.Post;
import com.snimma1.processor.PostProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/** @author snimmagadda1 Configuration class for batch jobs */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    public final JobBuilderFactory jobBuilderFactory;

    public final StepBuilderFactory stepBuilderFactory;

    /** Holds all readers available for job */
    private final ReadersConfig readers;

    /** Holds all writers available for job */
    private final WritersConfig writers;

    @Autowired
    public BatchConfig(
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            ReadersConfig readers,
            WritersConfig writers) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.readers = readers;
        this.writers = writers;
    }

    //    TODO: swap this
    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("spring_batch");
    }

    @Bean
    PostProcessor postProcessor() {
        return new PostProcessor();
    }

    @Bean
    public Job importStackDumpToSql(@Qualifier("step2") Step step2) {
        return jobBuilderFactory
                .get("importStackDump")
                .incrementer(new RunIdIncrementer())
                .flow(step2)
                .end()
                .build();
    }

    @Bean
    public SimpleFlow splitFlow(@Qualifier("step1") Step step1, @Qualifier("step2") Step step2) {
        SimpleFlow flow1 = new FlowBuilder<SimpleFlow>("f1").start(step1).build();
        SimpleFlow flow2 = new FlowBuilder<SimpleFlow>("f2").start(step2).build();
        return new FlowBuilder<SimpleFlow>("splitFlow")
                .split(taskExecutor())
                .add(flow1, flow2)
                .build();
    }

    @Bean
    public Step step1(
            @Qualifier("appJpaTransactionManager") JpaTransactionManager transactionManager,
            @Qualifier("appEntityManager") LocalContainerEntityManagerFactoryBean factory,
            PostProcessor postProcessor) {
        return stepBuilderFactory
                .get("posts")
                .transactionManager(transactionManager)
                .<Post, Post>chunk(100)
                .reader(readers.postsReader())
                .processor(postProcessor)
                .writer(writers.jpaItemWriter(factory))
                .faultTolerant()
                .build();
    }

    @Bean
    public Step step2(
            @Qualifier("appJpaTransactionManager") JpaTransactionManager transactionManager,
            @Qualifier("appEntityManager") LocalContainerEntityManagerFactoryBean factory) {
        return stepBuilderFactory
                .get("comments")
                .transactionManager(transactionManager)
                .<Comment, Comment>chunk(100)
                .reader(readers.commentsReader())
                .writer(writers.jpaItemWriter(factory))
                .faultTolerant()
                .build();
    }
}
