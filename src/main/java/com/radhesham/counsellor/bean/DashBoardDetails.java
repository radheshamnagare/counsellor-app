package com.radhesham.counsellor.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
public class DashBoardDetails {

    private int totalEnquiry;

    private int openEnquiry;

    private int enrolledEnquiry;

    private int lostEnquiry;
}
