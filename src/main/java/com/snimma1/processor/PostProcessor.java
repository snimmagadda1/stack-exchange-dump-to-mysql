package com.snimma1.processor;

import com.snimma1.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.batch.item.ItemProcessor;

import java.nio.charset.Charset;

@Slf4j
public class PostProcessor implements ItemProcessor<Post, Post> {

    @Override
    public Post process(Post in) throws Exception {

        Charset charset = Charset.forName("UTF-8");

        log.info("Processed post with title " + in.getTitle());
        return Post.builder()
                .id(in.getId())
                .postType(in.getPostType())
                .parentId(in.getParentId())
                .acceptedAnswerId(in.getAcceptedAnswerId())
                .creationDate(in.getCreationDate())
                .score(in.getScore())
                .viewCount(in.getViewCount())
                .body(StringEscapeUtils.unescapeHtml4(in.getBody()))
                .ownerUserId(in.getOwnerUserId())
                .ownerDisplayName(in.getOwnerDisplayName())
                .lastEditorUserId(in.getLastEditorUserId())
                .lastEditorDisplayName(in.getLastEditorDisplayName())
                .lastEditDate(in.getLastEditDate())
                .lastActivityDate(in.getLastActivityDate())
                .communityOwnedDate(in.getCommunityOwnedDate())
                .closedDate(in.getClosedDate())
                .title(StringEscapeUtils.unescapeHtml4(in.getTitle()))
                .tags(in.getTags())
                .answerCount(in.getAnswerCount())
                .commentCount(in.getCommentCount())
                .contentLicense(in.getContentLicense())
                .build();
    }
}
