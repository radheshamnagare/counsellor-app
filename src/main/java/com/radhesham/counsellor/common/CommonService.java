package com.radhesham.counsellor.common;

import com.radhesham.counsellor.bean.ResponseStatus;
import com.radhesham.counsellor.bean.SystemError;
import com.radhesham.counsellor.bean.response.DefaultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonService {
    private static final Logger logger = LoggerFactory.getLogger(CommonService.class);

    public static SystemError setErrorMessage(String errorCode, String val1, String val2) {
        SystemError error = new SystemError();
        try {
            logger.info("Entry in setErrorMessage()");
            switch (errorCode) {
                case ConstantPool.ERROR_CODE_SUCCESS: {
                    error.setErrorCode(errorCode);
                    error.setErrorStatus(ConstantPool.ERROR_STATUS_SUCCESS);
                    error.setErrorDescription(ConstantPool.ERROR_CODE_SUCCESS_DESC);
                }
                break;
                case ConstantPool.ERROR_CODE_DUPLICATE: {
                    error.setErrorCode(errorCode);
                    error.setErrorStatus(ConstantPool.ERROR_STATUS_DUPLICATE);
                    error.setErrorDescription(String.format(ConstantPool.ERROR_CODE_DUPLICATE_DESC, val1, val2));
                }
                break;
                case ConstantPool.ERROR_CODE_FAIL: {
                    error.setErrorCode(errorCode);
                    error.setErrorStatus(ConstantPool.ERROR_STATUS_FAIL);
                    error.setErrorDescription(ConstantPool.ERROR_CODE_FAIL_DESC);
                }
                break;
                case ConstantPool.ERROR_CODE_INVALID: {
                    error.setErrorCode(errorCode);
                    error.setErrorStatus(ConstantPool.ERROR_STATUS_INVALID);
                    error.setErrorDescription(String.format(ConstantPool.ERROR_CODE_INVALID_DESC, val1, val2));
                }
                break;
                case ConstantPool.ERROR_CODE_REQUIRED: {
                    error.setErrorCode(errorCode);
                    error.setErrorStatus(ConstantPool.ERROR_STATUS_REQUIRED);
                    error.setErrorDescription(String.format(ConstantPool.ERROR_CODE_REQUIRED_DESC, val1));
                }
                break;
                case ConstantPool.ERROR_CODE_UNKNOWN: {
                    error.setErrorCode(errorCode);
                    error.setErrorStatus(ConstantPool.ERROR_STATUS_UNKNOWN);
                    error.setErrorDescription(ConstantPool.ERROR_CODE_UNKNOWN_DESC);
                }
                break;
            }
            logger.info("Exist from setErrorMessage()");
        } catch (Exception e) {
            logger.error("Exception in setErrorMessage() :", e);
        }
        return error;
    }

    public static DefaultResponse getDefaultResponse(SystemError error,String apiKey,String token){
        DefaultResponse response=new DefaultResponse();
        try{
            logger.info("Entry in getDefaultResponse()");
            ResponseStatus status = new ResponseStatus();
            status.setErrorCode(error.getErrorCode());
            status.setErrorStatus(error.getErrorStatus());
            status.setErrorDescription(error.getErrorDescription());
            response.setResponseStatus(status);
            response.setApiKey(apiKey);
            response.setToken(token);
            logger.info("Exit from getDefaultResponse()");
        }catch (Exception e){
            logger.error("Exception from getDefaultResponse()");
        }
        return response;
    }
}
