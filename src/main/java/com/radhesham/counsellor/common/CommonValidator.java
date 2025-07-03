package com.radhesham.counsellor.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class CommonValidator {

    private static final Logger logger = LoggerFactory.getLogger(CommonValidator.class);

    public static boolean isEmpty(String _val) {
        boolean flag = false;
        try {
            logger.info("Entry in isEmpty()");
            if (Objects.isNull(_val) || _val.isEmpty()) {
                flag = true;
            }
            logger.info("Exit from isEmpty()");
        } catch (Exception e) {
            flag = false;
            logger.error("Exception in isEmpty() :", e);
        }
        return flag;
    }


    public static <T> boolean isEmptyList(List<T> _list) {
        boolean flag = false;
        try {
            logger.info("Entry in isEmptyList()");
            if (Objects.isNull(_list) || _list.isEmpty())
                flag = true;
            logger.info("Exit from isEmptyList()");
        } catch (Exception e) {
            flag = false;
            logger.error("Exception in isEmptyList() ", e);
        }
        return flag;
    }

    public static boolean isValidLength(int minLength, int maxLength, String _val) {
        boolean flag = true;
        try {
            logger.info("Entry in isValidLength()");
            if (minLength < 0 || maxLength < 0) {
                logger.warn("isValidLength() minlength & maxlength is ", minLength, maxLength);
                flag = false;
            } else if (Objects.isNull(_val)) {
                flag = false;
            } else if (_val.length() < minLength || _val.length() > maxLength) {
                flag = false;
            }
            logger.info("Exit from isValidLength()");
        } catch (Exception e) {
            flag = false;
            logger.error("Exception in isValidLength() ", e);
        }
        return flag;
    }

    private static boolean isValidPatten(String _val, String pattern) {
        boolean flag = false;
        try {
            logger.info("Entry in isValidPatten()");
            Pattern _pattern = Pattern.compile(pattern);
            flag = _pattern.matcher(_val).matches();
            logger.info("Exit from isValidPatten()");
        } catch (Exception e) {
            flag = false;
            logger.error("Exception in isValidPatten() ", e);
        }
        return flag;
    }

    public static boolean isNumeric(String _val) {
        boolean flag = true;
        try {
            logger.info("Entry in isNumeric()");
            if (Objects.isNull(_val)) {
                flag = false;
                logger.warn("isNumeric _val is null");
            } else if (!isValidPatten(_val, ConstantPool.NUMBER_PATTERN)) {
                flag = false;
            }
            logger.info("Exit from isNumeric()");
        } catch (Exception e) {
            flag = false;
            logger.error("Exception in isNumeric() ", e);
        }
        return flag;
    }

    public static boolean isValidEmail(String _val) {
        boolean flag = true;
        try {
            logger.info("Entry in isValidEmail()");
            if (Objects.isNull(_val)) {
                flag = false;
                logger.warn("isValidEmail _val is null");
            } else if (!isValidPatten(_val, ConstantPool.EMAIL_PATTERN)) {
                flag = false;
            }
            logger.info("Exit from isValidEmail()");
        } catch (Exception e) {
            logger.error("Exception in isValidEmail() ", e);
        }
        return flag;
    }

    public static boolean isValidMobileNo(String _val) {
        return isValidLength(ConstantPool.MIN_MOBILE_LENGTH, ConstantPool.MAX_MOBILE_LENGTH, _val) &&
                isNumeric(_val);
    }

    public static void main(String args[]){
        isValidEmail("radheshamnagaregamail.com");
        isNumeric("123");
        isNumeric("ff");
    }
}
