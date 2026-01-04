package com.metoak.mes.params.enums;

import com.metoak.mes.enums.BaseEnum;

public enum ParamTypeEnum implements BaseEnum {
    STEP(1, "工序"),
    FLOW(2, "工艺"),
    WORK_ORDER(3, "工单"),
    ENGINEERING(4, "工程"),
    USER(5, "用户");


    private final Integer code;
    private final String name;

    ParamTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

}