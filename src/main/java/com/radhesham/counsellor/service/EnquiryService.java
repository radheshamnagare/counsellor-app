package com.radhesham.counsellor.service;

import com.radhesham.counsellor.bean.DashBoardDetails;
import com.radhesham.counsellor.bean.request.EnquiryFilterRequest;
import com.radhesham.counsellor.entity.Enquiry;

import java.util.List;

public interface EnquiryService {

    boolean addEnquiry(Enquiry enquiry);

    List<Enquiry> getEnquiryByFilter(EnquiryFilterRequest enquiryFilterRequest);

    boolean isEnquiryIdExists(int id);

    DashBoardDetails getDashBoardDetails(String counsellorId);
}
