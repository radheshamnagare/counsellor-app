package com.radhesham.counsellor.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "enquiry-details")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnquiryDetails {

    @XmlElement(name = "id")
    @JsonProperty("id")
    int enquiryId;

    @XmlElement(name = "counsellor-id")
    @JsonProperty("counsellor-id")
    int counsellorId;

    @XmlElement(name = "name")
    @JsonProperty("name")
    String name;

    @XmlElement(name = "contact")
    @JsonProperty("contact")
    String contact;

    @XmlElement(name = "course")
    @JsonProperty("course")
    String course;

    @XmlElement(name = "class-mode")
    @JsonProperty("class-mode")
    String classMode;

    @XmlElement(name = "status")
    @JsonProperty("status")
    String status;
}
