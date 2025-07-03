package com.radhesham.counsellor.controller;

import com.radhesham.counsellor.bean.request.DashBoardLookupRequest;
import com.radhesham.counsellor.bean.response.DashBoardResponse;
import com.radhesham.counsellor.model.ManageDashBoard;
import com.radhesham.counsellor.service.CounsellorService;
import com.radhesham.counsellor.service.EnquiryService;
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
public class DashBoardController {

    @Autowired
    HttpServletRequest request;
    @Autowired
    EnquiryService enquiryService;
    @Autowired
    CounsellorService counsellorService;

    private static final Logger logger = LoggerFactory.getLogger(DashBoardController.class);

    @RequestMapping(value = "/dashboard",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<DashBoardResponse> getDashBoardDetails(@RequestBody DashBoardLookupRequest dashBoardLookupRequest){
        DashBoardResponse response = null;
        try{
            logger.info("Entry in getDashBoardDetails()");
            ManageDashBoard manageDashBoard = new ManageDashBoard();
            manageDashBoard.setRequest(request);
            manageDashBoard.setEnquiryService(enquiryService);
            manageDashBoard.setCounsellorService(counsellorService);
            response = manageDashBoard.getDashBoardDetails(dashBoardLookupRequest);
            logger.info("Exit from getDashBoardDetails()");
        }catch (Exception e){
            logger.error("Exception in getDashBoardDetails() ",e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
