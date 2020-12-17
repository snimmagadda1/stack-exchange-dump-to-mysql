package com.snimma1.custom;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Logs each content item by reflection with the {@link ToStringBuilder#reflectionToString(Object)}
 * method.
 *
 * @author Antoine
 *
 * @param <T>
 */
public class ConsoleItemWriter<T> implements ItemWriter<T> {

    private static final Logger LOG = LoggerFactory.getLogger(ConsoleItemWriter.class);

    @Override
    public void write(List<? extends T> items) throws Exception {
        LOG.trace("Console item writer start");
        for (T item : items) {
            LOG.info(ToStringBuilder.reflectionToString(item));
        }
        LOG.trace("Console item writer end");

    }
}