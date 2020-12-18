package com.snimma1.config.readers;

import com.snimma1.model.*;
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

    @Value("${import.file.badges}")
    private String badgesFile;

    @Value("${import.file.postHistory}")
    private String postHistoryFile;

    @Value("${import.file.users}")
    private String usersFile;

    @Value("${import.file.votes}")
    private String votesFile;

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

    @Bean
    public StaxEventItemReader badgesReader() {
        Jaxb2Marshaller unmarsh = new Jaxb2Marshaller();
        unmarsh.setClassesToBeBound(Badge.class);
        return new StaxEventItemReaderBuilder<Badge>()
                .name("badgeReader")
                .resource(new ClassPathResource(dir + "/" + badgesFile))
                .addFragmentRootElements("row")
                .unmarshaller(unmarsh)
                .build();
    }

    @Bean
    public StaxEventItemReader postHistoryReader() {
        Jaxb2Marshaller unmarsh = new Jaxb2Marshaller();
        unmarsh.setClassesToBeBound(PostHistory.class);
        return new StaxEventItemReaderBuilder<PostHistory>()
                .name("postHistoryReader")
                .resource(new ClassPathResource(dir + "/" + postHistoryFile))
                .addFragmentRootElements("row")
                .unmarshaller(unmarsh)
                .build();
    }

    @Bean
    public StaxEventItemReader usersReader() {
        Jaxb2Marshaller unmarsh = new Jaxb2Marshaller();
        unmarsh.setClassesToBeBound(User.class);
        return new StaxEventItemReaderBuilder<User>()
                .name("usersReader")
                .resource(new ClassPathResource(dir + "/" + usersFile))
                .addFragmentRootElements("row")
                .unmarshaller(unmarsh)
                .build();
    }

    @Bean
    public StaxEventItemReader votesReader() {
        Jaxb2Marshaller unmarsh = new Jaxb2Marshaller();
        unmarsh.setClassesToBeBound(Vote.class);
        return new StaxEventItemReaderBuilder<Vote>()
                .name("votesReader")
                .resource(new ClassPathResource(dir + "/" + votesFile))
                .addFragmentRootElements("row")
                .unmarshaller(unmarsh)
                .build();
    }
}
