package com.snimma1.config.readers;

import com.snimma1.model.Comment;
import com.snimma1.model.Post;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@PropertySource("classpath:application.yaml")
public class ReadersConfig {

    @Value("${import.file.dir}")
    private String dir;

    @Value("${import.file.posts}")
    private String postsFile;

    @Value("${import.file.comments}")
    private String commentsFile;

    @Bean
    public StaxEventItemReader postsReader() {
        Jaxb2Marshaller unmarsh = new Jaxb2Marshaller();
        unmarsh.setClassesToBeBound(Post.class);
        return new StaxEventItemReaderBuilder<Post>()
                .name("postReader")
                .resource(new ClassPathResource(dir + "/" + postsFile))
                .addFragmentRootElements("row")
                .unmarshaller(unmarsh)
                .build();
    }

    @Bean
    public StaxEventItemReader commentsReader() {
        Jaxb2Marshaller unmarsh = new Jaxb2Marshaller();
        unmarsh.setClassesToBeBound(Comment.class);
        return new StaxEventItemReaderBuilder<Comment>()
                .name("commentReader")
                .resource(new ClassPathResource(dir + "/" + commentsFile))
                .addFragmentRootElements("row")
                .unmarshaller(unmarsh)
                .build();
    }
}
