package com.metoak.mes.enums;

import java.math.BigDecimal;

public enum DefaultValueEnum {
    STRING(String.class, "deadbeef"),
    INTEGER(Integer.class, 0xdeadbeef),
    INT(int.class, 0xdeadbeef),
    LONG(Long.class, 0xdeadbeefL),
    PRIMITIVE_LONG(long.class, 0xdeadbeefL),
    FLOAT(Float.class, Float.intBitsToFloat(0xdeadbeef)),
    PRIMITIVE_FLOAT(float.class, Float.intBitsToFloat(0xdeadbeef)),
    DOUBLE(Double.class, Double.longBitsToDouble(0xdeadbeefL)),
    PRIMITIVE_DOUBLE(double.class, Double.longBitsToDouble(0xdeadbeefL)),
    BIG_DECIMAL(BigDecimal.class, new BigDecimal("3735928559")), // 十进制 0xdeadbeef
    BOOLEAN(Boolean.class, -1),  // 用 -1 表示布尔占位
    PRIMITIVE_BOOLEAN(boolean.class, -1);

    private final Class<?> type;
    private final Object defaultValue;

    DefaultValueEnum(Class<?> type, Object defaultValue) {
        this.type = type;
        this.defaultValue = defaultValue;
    }

    public Class<?> getType() {
        return type;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public static Object getDefault(Class<?> type) {
        for (DefaultValueEnum e : values()) {
            if (e.getType().equals(type)) {
                return e.getDefaultValue();
            }
        }
        return null;
    }
}