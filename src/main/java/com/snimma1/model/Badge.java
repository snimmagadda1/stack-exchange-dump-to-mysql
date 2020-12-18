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
@Entity(name = "badges")
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
public class Badge {

    @Id
    @XmlAttribute(name = "Id")
    private BigInteger id;

    @XmlAttribute(name = "UserId")
    private BigInteger userId;

    @XmlAttribute(name = "Name")
    private String name;

    @XmlAttribute(name = "Date")
    private String date;

    @XmlAttribute(name = "Class")
    private Integer badgeClass;

    @XmlAttribute(name = "TagBased")
    private Boolean tagBased;
}
