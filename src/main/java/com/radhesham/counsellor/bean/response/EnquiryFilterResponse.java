package com.radhesham.counsellor.bean.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.radhesham.counsellor.bean.EnquiryDetails;
import com.radhesham.counsellor.bean.ResponseStatus;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "enquiry-filter-response")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnquiryFilterResponse {

    @XmlElement(name = "status")
    @JsonProperty("status")
    private ResponseStatus status;

    @XmlElement(name = "enquiry-details")
    @JsonProperty("enquiry-details")
    private List<EnquiryDetails> enquiryDetails;

}
