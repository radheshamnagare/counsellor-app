package com.radhesham.counsellor.bean.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "enquiry-request")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnquiryRequest {

    @XmlElement(name = "counsellor-id")
    @JsonProperty("counsellor-id")
    String counsellorId;

    @XmlElement(name = "enquiry-id")
    @JsonProperty("enquiry-id")
    String enquiryId;

    @XmlElement(name = "name")
    @JsonProperty("name")
    String name;

    @XmlElement(name = "contact")
    @JsonProperty("contact")
    String contact;

    @XmlElement(name = "class-mode")
    @JsonProperty("class-mode")
    String classMode;

    @XmlElement(name = "course")
    @JsonProperty("course")
    String course;

    @XmlElement(name = "status")
    @JsonProperty("status")
    String status;
}
