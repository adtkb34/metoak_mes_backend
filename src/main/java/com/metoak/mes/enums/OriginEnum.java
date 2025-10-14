package com.metoak.mes.enums;

import java.util.Arrays;

public enum OriginEnum implements BaseEnum {

    SUZHOU(1, "苏州"),
    MIANYANG(2, "绵阳");

    private final Integer code;
    private final String name;

    OriginEnum(Integer code, String name) {
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

    public static OriginEnum fromCode(int code) {
        return Arrays.stream(values())
                .filter(originEnum -> originEnum.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("未知的产地编码: " + code));
    }
}
