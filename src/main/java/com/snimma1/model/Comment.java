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
@Entity(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
public class Comment {

    @Id
    @XmlAttribute(name = "Id")
    private BigInteger id;

    @XmlAttribute(name = "PostId")
    private BigInteger postId;

    @XmlAttribute(name = "Score")
    private Integer score;

    @XmlAttribute(name = "Text")
    private String text;

    @XmlAttribute(name = "CreationDate")
    private String creationDate;

    @XmlAttribute(name = "UserDisplayName")
    private String userDisplayName;

    @XmlAttribute(name = "UserId")
    private BigInteger userId;

    @XmlAttribute(name = "ContentLicense")
    private String contentLicense;
}
