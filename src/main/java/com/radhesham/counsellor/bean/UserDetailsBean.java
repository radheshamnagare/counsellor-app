package com.radhesham.counsellor.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "user-details-bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailsBean {

    @XmlElement(name = "id")
    @JsonProperty("id")
    String id;

    @XmlElement(name = "name")
    @JsonProperty("name")
    String name;

    @XmlElement(name = "contact")
    @JsonProperty("contact")
    String contact;

    @XmlElement(name = "password-expiry-date")
    @JsonProperty("password-expiry-date")
    String passwordExpiryDate;

    @XmlElement(name = "account-status")
    @JsonProperty("account-status")
    String accountStatus;

    @XmlElement(name = "password-status")
    @JsonProperty("password-status")
    String passwordStatus;

}
