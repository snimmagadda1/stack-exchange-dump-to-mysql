package com.snimma1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tags {

    @XmlAttribute(name = "Id")
    private BigInteger id;

    @XmlAttribute(name = "TagName")
    private String tagName;

    @XmlAttribute(name = "Count")
    private Integer count;

    @XmlAttribute(name = "ExcerptPostId")
    private BigInteger excerptPostId;

    @XmlAttribute(name = "WikiPostId")
    private BigInteger wikiPostId;

    @XmlAttribute(name = "IsModeratorOnly")
    private Boolean isModeratorOnly;

    @XmlAttribute(name = "IsRequired")
    private Boolean isRequired;
}
