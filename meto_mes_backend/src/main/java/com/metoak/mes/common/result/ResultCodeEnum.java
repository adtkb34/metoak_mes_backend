package com.metoak.mes.common.result;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(0, "成功"),
    FAIL(201, "失败"),

    EMPTY(2, "EMPTY"),
    FORM_VERIFICATION_FAILED(5, "FORM VERIFICATION FAILED"),
    LOGIN_FAILED(50, "LOGIN FAILED"),
    TOEKN_NULL(52, "TOEKN NULL"),
    TOKEN_INVALID(54, "TOKEN INVALID"),
    PRO_PROCESS_TYPE_NO_NOT_FOUND(102, "PRO_PROCESS_TYPE_NO_NOT_FOUND"),
    PRO_PRODUCT_SN_NOT_FOUND_BY_WORKORDER_NO(104, "PRO_PRODUCT_SN_NOT_FOUND_BY_WORKORDER_NO"),

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
    WORK_ORDER_NOT_FOUND(2003003, "未找到工单"),
    MULTIPLE_WORK_ORDERS_FOUND(2003004, "找到多个工单"),
    WORK_ORDER_QUERY_BY_ID_FAILED(2003005, "通过ID查询工单失败"),
    PARAMS_DETAIL_QUERY_BY_ID_FAILED(2003006, "通过ID查询参数详情失败"),
    PARAMS_BASE_QUERY_BY_ID_FAILED(2003007, "通过ID查询参数基础信息失败"),
    SN_NOT_FOUND(2003008, "在系统中未查询到此SN"),
    SN_DUPLICATED(2003009, "此SN在系统中重复出现"),
    FAILED_TO_FIND_PROCESS_BY_PROCESS_ID(2003010, "通过工艺编号没找到对应的工艺"),
    PARAMETER_NOT_PROVIDED(2003011, "参数没有传"),
    STEP_NOT_FOUND_BY_NO(2003012, "通过编号未找到工序"),

    // k3
    FAILED_TO_SAVE_BARCODE_MASTER(2004001, "保存条码主档失败"),
    FAILED_TO_SAVE_PACKING_RELATION(2004002, "保存装箱关系失败"),

    // material bind
    SN_SEQUENCE_EXCEEDS_MAX_LENGTH(2005001, "输入的数量太大，SN流水号超出设定长度"),
    ;


    private final Integer code;

    private final String name;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.name = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
