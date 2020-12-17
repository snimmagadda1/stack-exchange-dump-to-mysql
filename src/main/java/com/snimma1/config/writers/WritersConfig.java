package com.snimma1.config.writers;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;

import com.snimma1.model.Person;

@Configuration
@PropertySource("classpath:application.yaml")
public class WritersConfig {
    @Value("${export.file.csv}")
    private String csvFile;

    @Value("${export.file.txt}")
    private String txtFile;

    /** @return ItemWriter */
    @Bean
    public ItemWriter<Person> csvItemWriter() {
        FlatFileItemWriter<Person> csvWriter = new FlatFileItemWriter<>();
        csvWriter.setResource(new FileSystemResource(csvFile));
        csvWriter.setShouldDeleteIfExists(true);

        DelimitedLineAggregator<Person> lineAgg = new DelimitedLineAggregator<>();
        lineAgg.setDelimiter(",");
        BeanWrapperFieldExtractor<Person> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[] {"firstName", "lastName"});
        lineAgg.setFieldExtractor(extractor);
        csvWriter.setLineAggregator(lineAgg);
        return csvWriter;
    }

    /** @return ItemWriter */
    @Bean
    public ItemWriter<Person> txtItemWriter() {
        FlatFileItemWriter<Person> txtWriter = new FlatFileItemWriter<>();
        txtWriter.setResource(new FileSystemResource(txtFile));
        txtWriter.setShouldDeleteIfExists(true);

        DelimitedLineAggregator<Person> lineAgg = new DelimitedLineAggregator<>();
        lineAgg.setDelimiter("##");
        BeanWrapperFieldExtractor<Person> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[] {"firstName", "lastName"});
        lineAgg.setFieldExtractor(extractor);
        txtWriter.setLineAggregator(lineAgg);
        return txtWriter;
    }
}
