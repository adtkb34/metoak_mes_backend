package com.metoak.mes.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnum implements BaseEnum {
    SUCCESS(0, "SUCCESS"),
    FAIL(-1, "FAIL"),
    NULL(1, "NULL"),
    EMPTY(2, "EMPTY"),
    FORM_VERIFICATION_FAILED(5, "FORM VERIFICATION FAILED"),

    LOGIN_FAILED(50, "LOGIN FAILED"),
    LOGOUT_FAILED(51, "LOGOUT FAILED"),
    TOEKN_NULL(52, "TOEKN NULL"),
    TOKEN_EXPIRED(53, "TOKEN EXPIRED"),
    TOKEN_INVALID(54, "TOKEN INVALID"),

    PRO_PRODUCT_SN_NOT_FOUND(100, "PRO_PRODUCT_SN_NOT_FOUND"),
    PRO_DEVICE_NO_NOT_FOUND(101, "PRO_DEVICE_NO_NOT_FOUND"),
    PRO_PROCESS_TYPE_NO_NOT_FOUND(102, "PRO_PROCESS_TYPE_NO_NOT_FOUND"),
    PRO_TASK_NO_NOT_FOUND(103, "PRO_TASK_NO_NOT_FOUND"),
    PRO_PRODUCT_SN_NOT_FOUND_BY_WORKORDER_NO(104, "PRO_PRODUCT_SN_NOT_FOUND_BY_WORKORDER_NO"),
//    PRO_PRODUCT_SN_NOT_FOUND_BY_WORKORDER_NO(104, "PRO_PRODUCT_SN_NOT_FOUND_BY_WORKORDER_NO"),
    ;


    Integer code;
    String name;

    ResultCodeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}