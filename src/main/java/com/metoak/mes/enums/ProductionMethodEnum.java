package com.metoak.mes.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum ProductionMethodEnum implements BaseEnum {
    MANUAL(0, "手工"),
    AUTOMATED(1, "自动化");

    @EnumValue
    Integer code;
    String name;

    ProductionMethodEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @JsonValue
    public Map<String, String> toValue() {
        HashMap<String, String> map = new HashMap<>();
        map.put("label", name);
        map.put("value", code.toString());
        return map;
    }

}
