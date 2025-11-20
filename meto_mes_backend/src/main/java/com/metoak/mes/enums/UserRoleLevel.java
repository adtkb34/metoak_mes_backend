package com.metoak.mes.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum UserRoleLevel {
    L5(5, "操作工"),
    L4(4, "班组长"),
    L3(3, "工艺/设备工程师"),
    L2(2, "生产经理"),
    L1(1, "MES 管理员"),
    L0(0, "超级管理员");


    private final Integer code;
    private final String name;

    UserRoleLevel(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @JsonValue
    public Map<String, String> toValue() {
        HashMap<String, String> map = new HashMap<>();
        map.put("label", name);
        map.put("value", code.toString());
        return map;
    }
}