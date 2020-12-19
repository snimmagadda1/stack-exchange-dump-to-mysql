package com.snimma1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

@Data
@Entity(name = "post_history")
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostHistory {

    @Id
    @XmlAttribute(name = "Id")
    private BigInteger id;

    @XmlAttribute(name = "PostHistoryTypeId")
    private Integer postHistoryTypeId;

    @XmlAttribute(name = "PostId")
    private BigInteger postId;

    @XmlAttribute(name = "RevisionGUID")
    private String revisionGUID;

    @XmlAttribute(name = "CreationDate")
    private String creationDate;

    @XmlAttribute(name = "UserId")
    private BigInteger userId;

    @XmlAttribute(name = "UserDisplayName")
    private String userDisplayName;

    @XmlAttribute(name = "Comment")
    private String comment;

    @XmlAttribute(name = "CloseReasonTypes")
    private Integer closeReasonTypes;

    @XmlAttribute(name = "PostNoticeId")
    private BigInteger postNoticeId;

    @XmlAttribute(name = "Text")
    private String text;

    @XmlAttribute(name = "ContentLicense")
    private String contentLicense;
}
