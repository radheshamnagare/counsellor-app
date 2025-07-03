package com.radhesham.counsellor.model;

import com.radhesham.counsellor.bean.DashBoardDetails;
import com.radhesham.counsellor.bean.ResponseStatus;
import com.radhesham.counsellor.bean.SystemError;
import com.radhesham.counsellor.bean.request.DashBoardLookupRequest;
import com.radhesham.counsellor.bean.response.DashBoardResponse;
import com.radhesham.counsellor.common.CommonService;
import com.radhesham.counsellor.common.CommonValidator;
import com.radhesham.counsellor.common.ConstantPool;
import com.radhesham.counsellor.common.ErrorConstant;
import com.radhesham.counsellor.service.CounsellorService;
import com.radhesham.counsellor.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class ManageDashBoard {

    private static final Logger logger = LoggerFactory.getLogger(ManageDashBoard.class);
    private HttpServletRequest request;
    private EnquiryService enquiryService;
    private CounsellorService counsellorService;

    private boolean isValidCounsellorId(String id) {
        boolean flag = true;
        try {
            logger.info("Entry in isValidCounsellorId()");
            if (CommonValidator.isEmpty(id)) {
                flag = false;
            } else if (!CommonValidator.isNumeric(id)) {
                flag = false;
            } else if (!counsellorService.isCounsellorIdExist(id)) {
                flag = false;
            }
            logger.info("Exit from isValidCounsellorId()");
        } catch (Exception e) {
            flag = false;
            logger.error("Exception in isValidCounsellorId() :", e);
        }
        return flag;
    }

    private DashBoardResponse getDashBoardResponse(DashBoardDetails dashBoardDetails, SystemError error) {
        DashBoardResponse response = new DashBoardResponse();
        try {
            logger.info("Entry in getDashBoardResponse()");
            ResponseStatus status = new ResponseStatus();
            status.setErrorCode(error.getErrorCode());
            status.setErrorStatus(error.getErrorStatus());
            status.setErrorDescription(error.getErrorDescription());
            response.setStatus(status);
            response.setTotalEnquiry(dashBoardDetails.getTotalEnquiry());
            response.setEnrolledEnquiry(dashBoardDetails.getEnrolledEnquiry());
            response.setOpenEnquiry(dashBoardDetails.getOpenEnquiry());
            response.setLostEnquiry(dashBoardDetails.getLostEnquiry());
            logger.info("Exit from getDashBoardResponse()");
        } catch (Exception e) {
            logger.error("Exception in getDashBoardResponse() :", e);
        }
        return response;
    }

    public DashBoardResponse getDashBoardDetails(DashBoardLookupRequest dashBoardLookupRequest) {
        DashBoardResponse dashBoardResponse = new DashBoardResponse();
        SystemError error=null;
        try {
            logger.info("Entry in getDashBoardDetails()");
            if (isValidCounsellorId(dashBoardLookupRequest.getCounsellorId())) {
                DashBoardDetails dashBoardDetails = enquiryService.getDashBoardDetails(dashBoardLookupRequest.getCounsellorId());
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_SUCCESS,"","");
                dashBoardResponse = getDashBoardResponse(dashBoardDetails,error);
            } else {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_INVALID, ErrorConstant.DESC_COUNSELLOR_ID, dashBoardLookupRequest.getCounsellorId());
                dashBoardResponse = getDashBoardResponse(new DashBoardDetails(),error);
                logger.info("getDashBoardDetails() : invalid counsellor id passed ", dashBoardLookupRequest.getCounsellorId());
            }
            logger.info("Exit from getDashBoardDetails()");
        } catch (Exception e) {
            error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_FAIL, "","");
            dashBoardResponse = getDashBoardResponse(new DashBoardDetails(),error);
            logger.error("Exception in getDashBoardDetails() :", e);
        }
        return dashBoardResponse;
    }
}
