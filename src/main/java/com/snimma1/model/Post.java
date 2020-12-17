package com.snimma1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

@Data
@Builder
@Entity(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
public class Post {

    @Id
    @XmlAttribute(name = "Id")
    private BigInteger id;

    @XmlAttribute(name = "PostTypeId")
    private String postType;

    @XmlAttribute(name = "ParentId")
    private BigInteger parentId;

    @XmlAttribute(name = "AcceptedAnswerId")
    private BigInteger acceptedAnswerId;

    @XmlAttribute(name = "CreationDate")
    //    private XMLGregorianCalendar creationDate;
    private String creationDate;

    @XmlAttribute(name = "Score")
    private Integer score;

    @XmlAttribute(name = "ViewCount")
    private Integer viewCount;

    @Column(columnDefinition = "MEDIUMTEXT")
    @XmlAttribute(name = "Body")
    private String body;

    @XmlAttribute(name = "OwnerUserId")
    private Integer ownerUserId;

    @XmlAttribute(name = "OwnerDisplayName")
    private String ownerDisplayName;

    @XmlAttribute(name = "LastEditorUserId")
    private String lastEditorUserId;

    @XmlAttribute(name = "LastEditorDisplayName")
    private String lastEditorDisplayName;

    @XmlAttribute(name = "LastEditDate")
    //    private XMLGregorianCalendar lastEditDate;
    private String lastEditDate;

    @XmlAttribute(name = "LastActivityDate")
    //    private XMLGregorianCalendar lastActivityDate;
    private String lastActivityDate;

    @XmlAttribute(name = "CommunityOwnedDate")
    //    private XMLGregorianCalendar communityOwnedDate;
    private String communityOwnedDate;

    @XmlAttribute(name = "ClosedDate")
    //    private XMLGregorianCalendar closedDate;
    private String closedDate;

    @XmlAttribute(name = "Title")
    private String title;

    @XmlAttribute(name = "Tags")
    private String tags;

    @XmlAttribute(name = "AnswerCount")
    private Integer answerCount;

    @XmlAttribute(name = "CommentCount")
    private Integer commentCount;

    @XmlAttribute(name = "ContentLicense")
    private String contentLicense;
}
