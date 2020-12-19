package com.snimma1.config.batch;

import com.snimma1.config.readers.ReadersConfig;
import com.snimma1.config.writers.WritersConfig;
import com.snimma1.model.*;
import com.snimma1.processor.PostProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.configuration.TaskConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/** @author snimmagadda1 Configuration class for batch jobs */
@Configuration
@EnableBatchProcessing
@EnableTask
public class BatchConfig {

    public static final int BATCH_CHUNK_SIZE = 500;

    public TaskConfigurer taskConfigurer;

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
            TaskConfigurer taskConfigurer,
            ReadersConfig readers,
            WritersConfig writers) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.taskConfigurer = taskConfigurer;
        this.readers = readers;
        this.writers = writers;
    }

    public static final int CONCURRENCY_LIMIT = 10;

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor("batch_x");
        executor.setConcurrencyLimit(CONCURRENCY_LIMIT);
        return (TaskExecutor) executor;
    }

    @Bean
    PostProcessor postProcessor() {
        return new PostProcessor();
    }

    @Bean
    public Job importStackDumpToSql(Flow jobFlow) {
        return jobBuilderFactory
                .get("stackDump2SQL")
                .incrementer(new RunIdIncrementer())
                .start(jobFlow)
                .end()
                .build();
    }

    @Bean
    public Flow splitFlow(
            @Qualifier("step1") Step step1,
            @Qualifier("step2") Step step2,
            @Qualifier("step3") Step step3,
            @Qualifier("step4") Step step4,
            @Qualifier("step5") Step step5,
            @Qualifier("step6") Step step6) {
        final Flow f1 = new FlowBuilder<Flow>("s1").from(step1).end();
        final Flow f2 = new FlowBuilder<Flow>("s2").from(step2).end();
        final Flow f3 = new FlowBuilder<Flow>("s3").from(step3).end();
        final Flow f4 = new FlowBuilder<Flow>("s4").from(step4).end();
        final Flow f5 = new FlowBuilder<Flow>("s5").from(step5).end();
        final Flow f6 = new FlowBuilder<Flow>("s6").from(step6).end();

        return new FlowBuilder<SimpleFlow>("splitFlow")
                .split(taskExecutor())
                .add(f1, f2, f3, f4, f5, f6)
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
                .<Post, Post>chunk(BATCH_CHUNK_SIZE)
                .reader(readers.multiPostsReader())
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
                .<Comment, Comment>chunk(BATCH_CHUNK_SIZE)
                .reader(readers.multiCommentsReader())
                .writer(writers.jpaItemWriter(factory))
                .faultTolerant()
                .build();
    }

    @Bean
    public Step step3(
            @Qualifier("appJpaTransactionManager") JpaTransactionManager transactionManager,
            @Qualifier("appEntityManager") LocalContainerEntityManagerFactoryBean factory) {
        return stepBuilderFactory
                .get("badges")
                .transactionManager(transactionManager)
                .<Badge, Badge>chunk(BATCH_CHUNK_SIZE)
                .reader(readers.multiBadgesReader())
                .writer(writers.jpaItemWriter(factory))
                .faultTolerant()
                .build();
    }

    @Bean
    public Step step4(
            @Qualifier("appJpaTransactionManager") JpaTransactionManager transactionManager,
            @Qualifier("appEntityManager") LocalContainerEntityManagerFactoryBean factory) {
        return stepBuilderFactory
                .get("post_history")
                .transactionManager(transactionManager)
                .<PostHistory, PostHistory>chunk(BATCH_CHUNK_SIZE)
                .reader(readers.multiPostHistoryReader()) // todo
                .writer(writers.jpaItemWriter(factory))
                .faultTolerant()
                .build();
    }

    @Bean
    public Step step5(
            @Qualifier("appJpaTransactionManager") JpaTransactionManager transactionManager,
            @Qualifier("appEntityManager") LocalContainerEntityManagerFactoryBean factory) {
        return stepBuilderFactory
                .get("users")
                .transactionManager(transactionManager)
                .<User, User>chunk(BATCH_CHUNK_SIZE)
                .reader(readers.multiUsersReader()) // todo
                .writer(writers.jpaItemWriter(factory))
                .faultTolerant()
                .build();
    }

    @Bean
    public Step step6(
            @Qualifier("appJpaTransactionManager") JpaTransactionManager transactionManager,
            @Qualifier("appEntityManager") LocalContainerEntityManagerFactoryBean factory) {
        return stepBuilderFactory
                .get("votes")
                .transactionManager(transactionManager)
                .<User, User>chunk(BATCH_CHUNK_SIZE)
                .reader(readers.multiVotesReader()) // todo
                .writer(writers.jpaItemWriter(factory))
                .faultTolerant()
                .build();
    }
}
