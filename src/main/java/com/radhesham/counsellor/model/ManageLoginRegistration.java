package com.radhesham.counsellor.model;

import com.radhesham.counsellor.bean.ResponseStatus;
import com.radhesham.counsellor.bean.SystemError;
import com.radhesham.counsellor.bean.UserDetailsBean;
import com.radhesham.counsellor.bean.request.LoginRequest;
import com.radhesham.counsellor.bean.request.UserRegistrationRequest;
import com.radhesham.counsellor.bean.response.DefaultResponse;
import com.radhesham.counsellor.bean.response.LoginUserResponse;
import com.radhesham.counsellor.common.CommonService;
import com.radhesham.counsellor.common.CommonValidator;
import com.radhesham.counsellor.common.ConstantPool;
import com.radhesham.counsellor.common.ErrorConstant;
import com.radhesham.counsellor.entity.Counsellor;
import com.radhesham.counsellor.service.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@Data
public class ManageLoginRegistration {

    private static final Logger logger = LoggerFactory.getLogger(ManageLoginRegistration.class);
    private HttpServletRequest request;
    private CounsellorService loginRegistrationService;

    private SystemError isValidUserRegistrationRequest(UserRegistrationRequest userRegistrationRequest) {
        SystemError error = null;
        try {
            logger.info("Entry in isValidUserRegistrationRequest()");
            if (CommonValidator.isEmpty(userRegistrationRequest.getName())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_REQUIRED, ErrorConstant.DESC_NAME, "");
            } else if (CommonValidator.isEmpty(userRegistrationRequest.getEmail())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_REQUIRED, ErrorConstant.DESC_EMAIL, "");
            } else if (!CommonValidator.isValidEmail(userRegistrationRequest.getEmail())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_INVALID, ErrorConstant.DESC_EMAIL, userRegistrationRequest.getEmail());
            } else if (loginRegistrationService.isEmailExist(userRegistrationRequest.getEmail())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_DUPLICATE, ErrorConstant.DESC_EMAIL, userRegistrationRequest.getEmail());
            } else if (CommonValidator.isEmpty(userRegistrationRequest.getContact())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_REQUIRED, ErrorConstant.DESC_CONTACT, "");
            } else if (!CommonValidator.isValidMobileNo(userRegistrationRequest.getContact())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_INVALID, ErrorConstant.DESC_CONTACT, userRegistrationRequest.getContact());
            } else if (loginRegistrationService.isContactExist(userRegistrationRequest.getContact())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_DUPLICATE, ErrorConstant.DESC_CONTACT, userRegistrationRequest.getContact());
            } else if (CommonValidator.isEmpty(userRegistrationRequest.getPassword())) {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_REQUIRED, ErrorConstant.DESC_PASSWORD, "");
            } else {
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_SUCCESS, "", "");
            }
            logger.info("Exit from isValidUserRegistrationRequest()");
        } catch (Exception e) {
            logger.error("Exception in isValidUserRegistrationRequest() :", e);
        }
        return error;
    }

    private Counsellor getCounsellor(UserRegistrationRequest userRegistrationRequest) {
        Counsellor counsellor = new Counsellor();
        try {
            logger.info("Entry in getCounsellor()");
            counsellor.setName(userRegistrationRequest.getName());
            counsellor.setContact(userRegistrationRequest.getContact());
            counsellor.setEmailId(userRegistrationRequest.getEmail());
            counsellor.setPassword(userRegistrationRequest.getPassword());
            counsellor.setAccountStatus(ConstantPool.STATUS_ACTIVE);
            counsellor.setPasswordStatus(ConstantPool.STATUS_ACTIVE);
            logger.info("Exit from getCounsellor()");
        } catch (Exception e) {
            logger.error("Exception in getCounsellor() ", e);
        }
        return counsellor;
    }

    public DefaultResponse registerNewUser(UserRegistrationRequest userRegistrationRequest) {
        DefaultResponse response;
        SystemError error;
        try {
            logger.info("Entry in registerNewUser()");
            error = isValidUserRegistrationRequest(userRegistrationRequest);
            if (error.getErrorCode().equals(ConstantPool.ERROR_CODE_SUCCESS)) {
                Counsellor counsellor = getCounsellor(userRegistrationRequest);
                boolean result = loginRegistrationService.registerUser(counsellor);
                if (result) {
                    error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_SUCCESS, "", "");
                } else {
                    error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_FAIL, "", "");
                }
            }
            response = CommonService.getDefaultResponse(error, "", "");
            logger.info("Exit from registerNewUser()");
        } catch (Exception e) {
            error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_FAIL, "", "");
            response = CommonService.getDefaultResponse(error, "", "");
            logger.error("Exception in registerNewUser() :", e);
        }
        return response;
    }

    private SystemError isValidLoginRequest(LoginRequest logginRequest){
        SystemError error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_SUCCESS,"","");
        try{
            logger.info("Entry in isValidLoginRequest()");
            if(CommonValidator.isEmpty(logginRequest.getUserName())){
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_REQUIRED,ErrorConstant.DESC_USERNAME,"");
            }else if(CommonValidator.isEmpty(logginRequest.getPassword())){
                error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_REQUIRED,ErrorConstant.DESC_PASSWORD,"");
            }
            logger.info("Exit from isValidLoginRequest()");
        }catch (Exception e){
            logger.error("Exception in isValidLoginRequest() :",e);
        }
        return error;
    }

    private LoginUserResponse getLoginUserResponse(SystemError error,Counsellor counsellor){
        LoginUserResponse response=new LoginUserResponse();
        try{
            logger.info("Entry in getLoginUserResponse()");
            ResponseStatus status =new ResponseStatus();
            status.setErrorCode(error.getErrorCode());
            status.setErrorStatus(error.getErrorStatus());
            status.setErrorDescription(error.getErrorDescription());
            response.setStatus(status);
            UserDetailsBean userDetailsBean = new UserDetailsBean();
            if(Objects.nonNull(counsellor)){
                userDetailsBean.setId(String.valueOf(counsellor.getCounsellorId()));
                userDetailsBean.setName(counsellor.getName());
                userDetailsBean.setContact(counsellor.getContact());
                userDetailsBean.setPasswordStatus(counsellor.getPasswordStatus());
                userDetailsBean.setAccountStatus(counsellor.getAccountStatus());
            }
            response.setUserDetailsBean(userDetailsBean);
            logger.info("Exit from getLoginUserResponse()");
        }catch (Exception e){
            logger.error("Exception in getLoginUserResponse() :",e);
        }
        return response;
    }

    public LoginUserResponse loginUser(LoginRequest loginRequest){
        LoginUserResponse response =null;
        SystemError error;
        try {
            logger.info("Entry in loginUser()");
            error = isValidLoginRequest(loginRequest);
            if(error.getErrorCode().equals(ConstantPool.ERROR_CODE_SUCCESS)){
                Counsellor counsellor =loginRegistrationService.login(loginRequest);
                if(Objects.nonNull(counsellor)){
                    error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_SUCCESS,"","");
                }else{
                    error = CommonService.setErrorMessage(ConstantPool.ERROR_CODE_INVALID,ErrorConstant.DESC_INVALID_USER_DETAILS,"("+loginRequest.getUserName()+" and "+loginRequest.getPassword()+")");
                }
                response =getLoginUserResponse(error,counsellor);
            }else{
                response = getLoginUserResponse(error,null);
            }
            logger.info("Exit from loginUser()");
        }catch (Exception e){
            logger.error("Exception in loginUser() :",e);
        }
        return response;
    }
}
