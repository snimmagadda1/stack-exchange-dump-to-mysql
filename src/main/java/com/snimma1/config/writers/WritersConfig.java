package com.snimma1.config.writers;

import com.snimma1.custom.ConsoleItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@PropertySource("classpath:application.yaml")
public class WritersConfig {
  @Bean
  public ConsoleItemWriter consoleItemWriter() {
    return new ConsoleItemWriter();
  }

  @Bean
  public JpaItemWriter jpaItemWriter(
      @Qualifier("appEntityManager") LocalContainerEntityManagerFactoryBean factory) {
    JpaItemWriterBuilder builder = new JpaItemWriterBuilder();
    return builder.entityManagerFactory(factory.getObject()).build();
  }
}
