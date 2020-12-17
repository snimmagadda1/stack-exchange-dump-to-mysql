package com.snimma1.config.readers;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import com.snimma1.model.Person;

@Configuration
@PropertySource("classpath:application.yaml")
public class ReadersConfig {
    @Value("${import.file.csv}")
    private String csvFile;

    @Value("${import.file.txt}")
    private String txtFile;

    /** @return FlatFileItemReader Configured reader */
    @Bean
    public FlatFileItemReader<Person> readerTxt() {
        FlatFileItemReader<Person> readerPattern = new FlatFileItemReader<Person>();
        readerPattern.setResource(new ClassPathResource(txtFile));
        readerPattern.setLineMapper(
                new DefaultLineMapper<Person>() {
                    {
                        setLineTokenizer(
                                new DelimitedLineTokenizer() {
                                    {
                                        setDelimiter("##");
                                        setNames(new String[] {"firstName", "lastName"});
                                    }
                                });
                        setFieldSetMapper(
                                new BeanWrapperFieldSetMapper<Person>() {
                                    {
                                        setTargetType(Person.class);
                                    }
                                });
                    }
                });
        return readerPattern;
    }

    /** @return FlatFileItemReader Configured reader */
    @Bean
    public FlatFileItemReader<Person> readerCsv() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
        reader.setResource(new ClassPathResource(csvFile));
        reader.setLineMapper(
                new DefaultLineMapper<Person>() {
                    {
                        setLineTokenizer(
                                new DelimitedLineTokenizer() {
                                    {
                                        setNames(new String[] {"firstName", "lastName"});
                                    }
                                });
                        setFieldSetMapper(
                                new BeanWrapperFieldSetMapper<Person>() {
                                    {
                                        setTargetType(Person.class);
                                    }
                                });
                    }
                });
        return reader;
    }
}
