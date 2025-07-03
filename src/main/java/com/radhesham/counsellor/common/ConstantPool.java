package com.radhesham.counsellor.common;

public class ConstantPool {

    public static final Integer MIN_MOBILE_LENGTH=10;
    public static final Integer MAX_MOBILE_LENGTH=14;

    public static final String NUMBER_PATTERN="^\\d+$";
    public static final String EMAIL_PATTERN="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static final String ERROR_CODE_SUCCESS="000";
    public static final String ERROR_CODE_SUCCESS_DESC="success";
    public static final String ERROR_STATUS_SUCCESS="success";

    public static final String ERROR_CODE_UNKNOWN="001";
    public static final String ERROR_CODE_UNKNOWN_DESC="unknown";
    public static final String ERROR_STATUS_UNKNOWN="success";

    public static final String ERROR_CODE_FAIL="003";
    public static final String ERROR_CODE_FAIL_DESC="fail to execute";
    public static final String ERROR_STATUS_FAIL="fail";

    public static final String ERROR_CODE_DUPLICATE="004";
    public static final String ERROR_CODE_DUPLICATE_DESC="%s is duplicate,%s";
    public static final String ERROR_STATUS_DUPLICATE="duplicate";

    public static final String ERROR_CODE_INVALID="005";
    public static final String ERROR_CODE_INVALID_DESC="%s is invalid, %s";
    public static final String ERROR_STATUS_INVALID="invalid";

    public static final String ERROR_CODE_REQUIRED="006";
    public static final String ERROR_CODE_REQUIRED_DESC="%s is required";
    public static final String ERROR_STATUS_REQUIRED="required";

    public static final String STATUS_ACTIVE="active";
    public static final String STATUS_INACTIVE="inactive";

    public static final String STATUS_OPEN="open";
    public static final String STATUS_ENROLLED="enrolled";
    public static final String STATUS_LOST="lost";

    public static final String CLASS_MODE_ONLINE="online";
    public static final String CLASS_MODE_OFFLINE="offline";

    public static final String ACTION_ADD="add";
    public static final String ACTION_UPDATE="update";
}
