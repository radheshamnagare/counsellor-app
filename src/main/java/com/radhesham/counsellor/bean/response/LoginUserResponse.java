package com.radhesham.counsellor.bean.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.radhesham.counsellor.bean.ResponseStatus;
import com.radhesham.counsellor.bean.UserDetailsBean;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "login-response")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginUserResponse {

    @XmlElement(name = "status")
    @JsonProperty("status")
    private ResponseStatus status;

    @XmlElement(name = "user-details")
    @JsonProperty("user-details")
    private UserDetailsBean userDetailsBean;
}
