package com.metoak.mes.params.enums;

import com.metoak.mes.enums.BaseEnum;

public enum ParamTypeEnum implements BaseEnum {
    PROCESS(1, "工艺"),
    ENGINEERING(2, "工程");


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