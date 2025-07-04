package com.radhesham.counsellor.bean.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "user-registration-request")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegistrationRequest {

    @XmlElement(name = "name")
    @JsonProperty("name")
    String name;

    @XmlElement(name = "contact")
    @JsonProperty("contact")
    String contact;

    @XmlElement(name = "email")
    @JsonProperty("email")
    String email;

    @XmlElement(name = "password")
    @JsonProperty("password")
    String password;
}
