package com.radhesham.counsellor.bean.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "dashboard-lookup-request")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashBoardLookupRequest {

    @XmlElement(name = "counsellor-id")
    @JsonProperty("counsellor-id")
    String counsellorId;

    @XmlElement(name = "status")
    @JsonProperty("status")
    String status;
}
