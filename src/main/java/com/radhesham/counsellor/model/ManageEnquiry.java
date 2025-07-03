package com.radhesham.counsellor.model;

import com.radhesham.counsellor.bean.EnquiryDetails;
import com.radhesham.counsellor.bean.ResponseStatus;
import com.radhesham.counsellor.bean.SystemError;
import com.radhesham.counsellor.bean.request.EnquiryFilterRequest;
import com.radhesham.counsellor.bean.request.EnquiryRequest;
import com.radhesham.counsellor.bean.response.DefaultResponse;
import com.radhesham.counsellor.bean.response.EnquiryFilterResponse;
import com.radhesham.counsellor.common.CommonService;
import com.radhesham.counsellor.common.CommonValidator;
import com.radhesham.counsellor.common.ConstantPool;
import com.radhesham.counsellor.common.ErrorConstant;
import com.radhesham.counsellor.entity.Counsellor;
import com.radhesham.counsellor.entity.Enquiry;
import com.radhesham.counsellor.service.EnquiryService;
import com.radhesham.counsellor.service.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class ManageEnquiry {
    private static final Logger logger = LoggerFactory.getLogger(ManageEnquiry.class);
    private HttpServletRequest request;
    private EnquiryService enquiryService;
    private CounsellorService authService;

    private boolean isValidStatus(String status) {
        boolean res = false;
        try {
            res = switch (status) {
                case ConstantPool.STATUS_OPEN, ConstantPool.STATUS_LOST, ConstantPool.STATUS_ENROLLED -> true;
                default -> res;
            };
        } catch (Exception e) {
            logger.error("Exception in isValidStatus() :", e);
        }
        return res;
    }

    private boolean isValidClassMode(String classMode) {
        boolean res = false;
        try {
            res = switch (classMode) {
                case ConstantPool.CLASS_MODE_ONLINE, ConstantPool.CLASS_MODE_OFFLINE -> true;
                default -> res;
            };
        } catch (Exception e) {
            logger.error("Exception in validClassMode() :", e);
        }
        return res;
    }

    private boolean isValidCourse(String course) {
        boolean res = false;
        try {
            if (!CommonValidator.isNumeric(course)) {
                res = true;
            }
        } catch (Exception e) {
            logger.error("Exception in isValidCourse() :", e);
        }
        return res;
    }

    private boolean isValidName(String name) {
        boolean res = false;
        try {
            if (!CommonValidator.isNumeric(name)) {
                res = true;
            }
        } catch (Exception e) {
            logger.error("Exception in isValidName() :", e);
        }
        return res;
    }

    private boolean isValidCounsellorId(String id) {
        boolean res = false;
        try {
            if (CommonValidator.isNumeric(id)) {
                res = authService.isCounsellorIdExist(id);
            }
        } catch (Exception e) {
            logger.error("Exception in isValidCounsellorId() :", e);
        }
        return res;
    }

    private boolean isValidEnquiryId(String id) {
        boolean flag = false;
        try {
            logger.info("Entry in isValidEnquiryId()");
            if (CommonValidator.isNumeric(id)) {
                flag = enquiryService.isEnquiryIdExists(Integer.parseInt(id));
            }
            logger.info("Exit from isValidEnquiryId()");
        } catch (Exception e) {
            logger.error("Exception in isValidEnquiryId() ", e);
        }
        return flag;
    }

    private SystemError isValidAddEnquiryRequest(EnquiryRequest enquiryRequest, String action) {
        SystemError error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_SUCCESS, "", "");
        try {
            logger.info("Entry in isValidAddEnquiryRequest()");
            if (action.equals(ConstantPool.ACTION_UPDATE) && CommonValidator.isEmpty(enquiryRequest.getEnquiryId())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_REQUIRED, ErrorConstant.DESC_ENQUIRY_ID, "");
            } else if (action.equals(ConstantPool.ACTION_UPDATE) && !isValidEnquiryId(enquiryRequest.getEnquiryId())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_INVALID, ErrorConstant.DESC_ENQUIRY_ID, "");
            } else if (CommonValidator.isEmpty(enquiryRequest.getCounsellorId())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_REQUIRED, ErrorConstant.DESC_COUNSELLOR_ID, "");
            } else if (!isValidCounsellorId(enquiryRequest.getCounsellorId())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_INVALID, ErrorConstant.DESC_COUNSELLOR_ID, enquiryRequest.getCounsellorId());
            } else if (CommonValidator.isEmpty(enquiryRequest.getName())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_REQUIRED, ErrorConstant.DESC_NAME, "");
            } else if (!isValidName(enquiryRequest.getName())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_INVALID, ErrorConstant.DESC_NAME, enquiryRequest.getName());
            } else if (CommonValidator.isEmpty(enquiryRequest.getContact())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_REQUIRED, ErrorConstant.DESC_CONTACT, "");
            } else if (!CommonValidator.isValidMobileNo(enquiryRequest.getContact())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_INVALID, ErrorConstant.DESC_CONTACT, enquiryRequest.getContact());
            } else if (CommonValidator.isEmpty(enquiryRequest.getClassMode())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_REQUIRED, ErrorConstant.DESC_CLASS_MODE, "");
            } else if (!isValidClassMode(enquiryRequest.getClassMode())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_INVALID, ErrorConstant.DESC_CLASS_MODE, enquiryRequest.getClassMode());
            } else if (CommonValidator.isEmpty(enquiryRequest.getCourse())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_REQUIRED, ErrorConstant.DESC_CONTACT, "");
            } else if (!isValidCourse(enquiryRequest.getCourse())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_INVALID, ErrorConstant.DESC_COURSE, enquiryRequest.getCourse());
            } else if (CommonValidator.isEmpty(enquiryRequest.getStatus())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_REQUIRED, ErrorConstant.DESC_STATUS, "");
            } else if (!isValidStatus(enquiryRequest.getStatus())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_INVALID, ErrorConstant.DESC_STATUS, enquiryRequest.getStatus());
            }
            logger.info("Exit from isValidAddEnquiryRequest()");
        } catch (Exception e) {
            logger.error("Exception in isValidAddEnquiryRequest() :", e);
        }
        return error;
    }

    private Enquiry getEnquiry(EnquiryRequest enquiryRequest) {
        Enquiry enquiry = null;
        try {
            if (Objects.nonNull(enquiryRequest)) {
                enquiry = new Enquiry();
                enquiry.setEnquiryId(enquiry.getEnquiryId());
                Counsellor _counsellor = new Counsellor();
                _counsellor.setCounsellorId(Integer.parseInt(enquiryRequest.getCounsellorId()));
                enquiry.setCounsellor(_counsellor);
                enquiry.setName(enquiryRequest.getName());
                enquiry.setContact(enquiryRequest.getContact());
                enquiry.setCourse(enquiryRequest.getCourse());
                enquiry.setClassMode(enquiryRequest.getClassMode());
                enquiry.setStatus(enquiryRequest.getStatus());
            }
        } catch (Exception e) {
            logger.error("Exception in getEnquiry() :", e);
        }
        return enquiry;
    }

    public DefaultResponse addUpdateEnquiryDetails(EnquiryRequest enquiryRequest, String action) {
        DefaultResponse response = null;
        SystemError error;
        try {
            logger.info("Entry in addEnquiryDetails()");
            error = isValidAddEnquiryRequest(enquiryRequest, action);
            if (error.getErrorCode().equals(ConstantPool.ERROR_CODE_SUCCESS)) {
                Enquiry enquiry = getEnquiry(enquiryRequest);
                boolean res = enquiryService.addEnquiry(enquiry);
                if (res) {
                    error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_SUCCESS, "", "");
                } else {
                    error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_FAIL, "", "");
                }
                response = CommonService.getDefaultResponse(error, "", "");
            } else {
                response = CommonService.getDefaultResponse(error, "", "");
            }
            logger.info("Exit from addEnquiryDetails()");
        } catch (Exception e) {
            error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_FAIL, "", "");
            response = CommonService.getDefaultResponse(error, "", "");
            logger.error("Exception in addEnquiryDetails() :", e);
        }
        return response;
    }

    private List<EnquiryDetails> getEnquiryDetailsList(List<Enquiry> enquiryList) {
        List<EnquiryDetails> enquiryDetails = new ArrayList<>();
        try {
            logger.info("Entry in getEnquiryDetailsList()");
            enquiryList.forEach(el -> {
                EnquiryDetails _enquiry = new EnquiryDetails();
                _enquiry.setEnquiryId(el.getEnquiryId());
                _enquiry.setCounsellorId(el.getCounsellor().getCounsellorId());
                _enquiry.setName(el.getName());
                _enquiry.setContact(el.getContact());
                _enquiry.setCourse(el.getCourse());
                _enquiry.setClassMode(el.getClassMode());
                _enquiry.setStatus(el.getStatus());
                enquiryDetails.add(_enquiry);
            });
            logger.info("Exit from getEnquiryDetailsList()");
        } catch (Exception e) {
            logger.error("Exception in getEnquiryDetailsList() ", e);
        }
        return enquiryDetails;
    }

    private EnquiryFilterResponse getEnquiryFilterResponse(List<Enquiry> enquiryList, SystemError error) {
        EnquiryFilterResponse enquiryFilterResponse = new EnquiryFilterResponse();
        try {
            logger.info("Entry in getEnquiryFilterResponse()");
            ResponseStatus status = new ResponseStatus();
            status.setErrorCode(error.getErrorCode());
            status.setErrorStatus(error.getErrorStatus());
            status.setErrorDescription(error.getErrorDescription());
            enquiryFilterResponse.setStatus(status);
            enquiryFilterResponse.setEnquiryDetails(getEnquiryDetailsList(enquiryList));
            logger.info("Exit from getEnquiryFilterResponse()");
        } catch (Exception e) {
            logger.error("Exception in getEnquiryFilterResponse() ", e);
        }
        return enquiryFilterResponse;
    }

    public EnquiryFilterResponse getFilterEnquiries(EnquiryFilterRequest enquiryFilterRequest) {
        EnquiryFilterResponse enquiryFilterResponseList = null;
        SystemError error = null;
        try {
            logger.info("Entry in getFilterEnquiries()");
            List<Enquiry> enquiryDetails = enquiryService.getEnquiryByFilter(enquiryFilterRequest);
            if (CommonValidator.isEmptyList(enquiryDetails)) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_REQUIRED, ErrorConstant.DESC_DETAILS_NOT_FOUND, "");
            } else {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_SUCCESS, "", "");
            }
            enquiryFilterResponseList = getEnquiryFilterResponse(enquiryDetails, error);
            logger.info("Exit from getFilterEnquiries()");
        } catch (Exception e) {
            logger.error("Exception in getFilterEnquiries() ", e);
        }
        return enquiryFilterResponseList;
    }
}
