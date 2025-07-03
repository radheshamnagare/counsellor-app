package com.radhesham.counsellor.controller;

import com.radhesham.counsellor.bean.request.EnquiryFilterRequest;
import com.radhesham.counsellor.bean.request.EnquiryRequest;
import com.radhesham.counsellor.bean.response.DefaultResponse;
import com.radhesham.counsellor.bean.response.EnquiryFilterResponse;
import com.radhesham.counsellor.common.ConstantPool;
import com.radhesham.counsellor.model.ManageEnquiry;
import com.radhesham.counsellor.service.EnquiryService;
import com.radhesham.counsellor.service.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnquiryController {

    private static final Logger logger = LoggerFactory.getLogger(EnquiryController.class);
    @Autowired
    HttpServletRequest request;

    @Autowired
    EnquiryService enquiryService;

    @Autowired
    CounsellorService loginRegistrationService;

    @RequestMapping(value = "/lookup", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<EnquiryFilterResponse> enquiryLookup(@RequestBody EnquiryFilterRequest enquiryFilterRequest) {
        EnquiryFilterResponse enquiryFilterResponseList = null;
        try {
            logger.info("Entry in enquiryLookup()");
            ManageEnquiry manageEnquiry = new ManageEnquiry();
            manageEnquiry.setRequest(request);
            manageEnquiry.setEnquiryService(enquiryService);
            manageEnquiry.setAuthService(loginRegistrationService);
            enquiryFilterResponseList = manageEnquiry.getFilterEnquiries(enquiryFilterRequest);
            logger.info("Exit from enquiryLookup()");
        } catch (Exception e) {
            logger.error("Exception in enquiryLookup() ", e);
        }
        return new ResponseEntity<>(enquiryFilterResponseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/addEnquiry", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<DefaultResponse> saveEnquiry(@RequestBody EnquiryRequest enquiryRequest) {
        DefaultResponse response = null;
        try {
            logger.info("Entry in saveEnquiry()");
            ManageEnquiry manageEnquiry = new ManageEnquiry();
            manageEnquiry.setRequest(request);
            manageEnquiry.setEnquiryService(enquiryService);
            manageEnquiry.setAuthService(loginRegistrationService);
            response = manageEnquiry.addUpdateEnquiryDetails(enquiryRequest,ConstantPool.ACTION_ADD);
            logger.info("Exit from saveEnquiry()");
        } catch (Exception e) {
            logger.error("Exception in saveEnquiry() :", e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateEnquiry", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<DefaultResponse> updateEnquiry(@RequestBody EnquiryRequest enquiryRequest) {
        DefaultResponse response = null;
        try {
            logger.info("Entry in updateEnquiry()");
            ManageEnquiry manageEnquiry = new ManageEnquiry();
            manageEnquiry.setRequest(request);
            manageEnquiry.setEnquiryService(enquiryService);
            manageEnquiry.setAuthService(loginRegistrationService);
            response = manageEnquiry.addUpdateEnquiryDetails(enquiryRequest, ConstantPool.ACTION_UPDATE);
            logger.info("Exit from updateEnquiry()");
        } catch (Exception e) {
            logger.error("Exception in updateEnquiry() :", e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
