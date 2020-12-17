package com.snimma1.config.writers;

import com.snimma1.custom.ConsoleItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yaml")
public class WritersConfig {
    @Value("${export.file.csv}")
    private String csvFile;

    @Value("${export.file.txt}")
    private String txtFile;

    @Bean
    public ConsoleItemWriter consoleItemWriter() {
        return new ConsoleItemWriter();
    }
}
