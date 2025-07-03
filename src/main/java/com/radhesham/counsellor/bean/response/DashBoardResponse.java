package com.radhesham.counsellor.bean.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.radhesham.counsellor.bean.ResponseStatus;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "dashboard-response")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashBoardResponse {

    @XmlElement(name = "status")
    @JsonProperty("status")
    private ResponseStatus status;

    @XmlElement(name = "total-enquiry")
    @JsonProperty("total-enquiry")
    private int totalEnquiry;

    @XmlElement(name = "open-enquiry")
    @JsonProperty("open-enquiry")
    private int openEnquiry;

    @XmlElement(name = "enrolled-enquiry")
    @JsonProperty("enrolled-enquiry")
    private int enrolledEnquiry;

    @XmlElement(name = "lost-enquiry")
    @JsonProperty("lost-enquiry")
    private int lostEnquiry;
}
