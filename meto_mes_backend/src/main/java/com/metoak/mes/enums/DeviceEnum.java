package com.metoak.mes.enums;

import java.util.Arrays;

public enum DeviceEnum implements BaseEnum {

    GUANGHAOJIE(1, "广浩捷"),
    SHUNYU(2, "舜宇"),
    AIWEISHI(3, "艾薇视");

    private final Integer code;
    private final String name;

    DeviceEnum(Integer code, String name) {
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

//    public boolean requiresPositionOffset() {
//        return this == GUANGHAOJIE || this == SHUNYU;
//    }
//
//    public boolean usesMoAutoAdjustSt08() {
//        return this == SHUNYU || this == AIWEISHI;
//    }

    public static DeviceEnum fromCode(int code) {
        return Arrays.stream(values())
                .filter(deviceEnum -> deviceEnum.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("未知的设备编码: " + code));
    }
}
