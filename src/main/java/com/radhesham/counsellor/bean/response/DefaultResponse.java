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
@XmlRootElement(name = "default-response")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultResponse {

    @XmlElement(name = "status")
    @JsonProperty("status")
    ResponseStatus responseStatus;

    @XmlElement(name="api-key")
    @JsonProperty("api-key")
    String apiKey;

    @XmlElement(name="token")
    @JsonProperty("token")
    String token;
}
