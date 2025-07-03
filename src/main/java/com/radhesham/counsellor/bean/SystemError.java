package com.radhesham.counsellor.bean;

import lombok.Data;

@Data
public class SystemError {
    String errorCode = "UNKNOWN";
    String errorStatus = "UNKNOWN";
    String errorDescription = "UNKNOWN";
}
