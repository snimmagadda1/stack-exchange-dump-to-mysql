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
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @Id
    @XmlAttribute(name = "Id")
    private BigInteger id;

    @XmlAttribute(name = "Reputation")
    private Integer reputation;

    @XmlAttribute(name = "CreationDate")
    private String creationDate;

    @XmlAttribute(name = "DisplayName")
    private String displayName;

    @XmlAttribute(name = "LastAccessedDate")
    private String lastAccessedDate;

    @XmlAttribute(name = "WebsiteUrl")
    private String websiteUrl;

    @XmlAttribute(name = "Location")
    private String location;

    @XmlAttribute(name = "AboutMe")
    private String aboutMe;

    @XmlAttribute(name = "Views")
    private Integer views;

    @XmlAttribute(name = "UpVotes")
    private Integer upVotes;

    @XmlAttribute(name = "DownVotes")
    private Integer downVotes;

    @XmlAttribute(name = "ProfileImageUrl")
    private String profileImageUrl;

    @XmlAttribute(name = "EmailHash")
    private String emailHash;

    @XmlAttribute(name = "AccountId")
    private BigInteger accountId;
}
