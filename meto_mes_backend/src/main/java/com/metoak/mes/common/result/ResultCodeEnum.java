package com.metoak.mes.common.result;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(0, "成功"),
    FAIL(201, "失败"),


    // IMU CALIB
    NO_IMU_CALCULATION_RECORD(2001001, "没有IMU计算记录"),
    LATEST_IMU_CALCULATION_FAILED(2001002, "最近的IMU计算记录失败"),
    IMU_CALCULATION_RECORD_NOT_LINKED_TO_STEREO_CALIBRATION(2001003, "IMU计算记录没有关联双目标定记录"),
    STEREO_CALIBRATION_RECORD_NOT_FOUND(2001004, "找不到对应的双目标定记录"),
    HAS_NULL_VALUE(2001005, "存在空值"),

    // 双目标定
    Field_NOT_FOUND(2002001, "字段没有找到"),
    RECORD_NOT_FOUND(2002002, "没有找到记录"),

    // 参数模块
    PARAM_BASE_NOT_FOUND(2003001, "参数集基础信息不存在"),
    PARAMS_INVALID_JSON(2003002, "参数配置不是有效的 JSON"),
    ;


    private final Integer code;

    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
