package com.snimma1.config.readers;

import com.snimma1.model.*;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
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
    public ItemReader multiPostsReader() {
        Resource[] resources = null;
        //        ResourcePatternResolver patternResolver = new
        // PathMatchingResourcePatternResolver();
        //        try {
        //            resources = patternResolver.getResources(dir + postsFile);
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        Resource fileSystem = new FileSystemResource(dir + "/" + postsFile);
        System.out.println("READING FILES AT THE DIR " + dir + postsFile);
        resources = new Resource[] {fileSystem};
        MultiResourceItemReader<String> reader = new MultiResourceItemReader<>();
        reader.setResources(resources);
        reader.setDelegate(postsReader());
        return reader;
    }

    @Bean
    public ItemReader multiBadgesReader() {
        Resource[] resources = null;
        //        ResourcePatternResolver patternResolver = new
        // PathMatchingResourcePatternResolver();
        //        try {
        //            resources = patternResolver.getResources(dir + badgesFile);
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        Resource fileSystem = new FileSystemResource(dir + "/" + badgesFile);
        resources = new Resource[] {fileSystem};
        MultiResourceItemReader<String> reader = new MultiResourceItemReader<>();
        reader.setResources(resources);
        reader.setDelegate(badgesReader());
        return reader;
    }

    @Bean
    public ItemReader multiPostHistoryReader() {
        Resource[] resources = null;
        //        ResourcePatternResolver patternResolver = new
        // PathMatchingResourcePatternResolver();
        //        try {
        //            resources = patternResolver.getResources(dir + postHistoryFile);
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }

        Resource fileSystem = new FileSystemResource(dir + "/" + postHistoryFile);
        resources = new Resource[] {fileSystem};
        MultiResourceItemReader<String> reader = new MultiResourceItemReader<>();
        reader.setResources(resources);
        reader.setDelegate(postHistoryReader());
        return reader;
    }

    @Bean
    public ItemReader multiCommentsReader() {
        Resource[] resources = null;
        //        ResourcePatternResolver patternResolver = new
        // PathMatchingResourcePatternResolver();
        //        try {
        //            resources = patternResolver.getResources(dir + commentsFile);
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }

        Resource fileSystem = new FileSystemResource(dir + "/" + commentsFile);
        resources = new Resource[] {fileSystem};
        MultiResourceItemReader<String> reader = new MultiResourceItemReader<>();
        reader.setResources(resources);
        reader.setDelegate(commentsReader());
        return reader;
    }

    @Bean
    public ItemReader multiUsersReader() {
        Resource[] resources = null;
        //        ResourcePatternResolver patternResolver = new
        // PathMatchingResourcePatternResolver();
        //        try {
        //            resources = patternResolver.getResources(dir + usersFile);
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }

        Resource fileSystem = new FileSystemResource(dir + "/" + usersFile);
        resources = new Resource[] {fileSystem};
        MultiResourceItemReader<String> reader = new MultiResourceItemReader<>();
        reader.setResources(resources);
        reader.setDelegate(usersReader());
        return reader;
    }

    @Bean
    public ItemReader multiVotesReader() {
        Resource[] resources = null;
        //        ResourcePatternResolver patternResolver = new
        // PathMatchingResourcePatternResolver();
        //        try {
        //            resources = patternResolver.getResources(dir + votesFile);
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }

        Resource fileSystem = new FileSystemResource(dir + "/" + votesFile);
        resources = new Resource[] {fileSystem};
        MultiResourceItemReader<String> reader = new MultiResourceItemReader<>();
        reader.setResources(resources);
        reader.setDelegate(votesReader());
        return reader;
    }

    //  ******  Singleton readers ******
    @Bean
    public StaxEventItemReader postsReader() {
        Jaxb2Marshaller unmarsh = new Jaxb2Marshaller();
        unmarsh.setClassesToBeBound(Post.class);
        return new StaxEventItemReaderBuilder<Post>()
                .name("postReader")
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
                .addFragmentRootElements("row")
                .unmarshaller(unmarsh)
                .build();
    }
}
