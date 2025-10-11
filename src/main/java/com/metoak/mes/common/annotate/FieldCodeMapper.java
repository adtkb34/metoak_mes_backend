package com.metoak.mes.common.annotate;

import com.metoak.mes.dto.AttrKeyValDto;
import com.metoak.mes.enums.DefaultValueEnum;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FieldCodeMapper {

    public static void applyAttrListToObject(Object obj, List<AttrKeyValDto> dtoList) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (AttrKeyValDto dto : dtoList) {
            Map<String, String> typeValueMap = new HashMap<>();
            typeValueMap.put("val", dto.getVal());
            typeValueMap.put("lsl", dto.getLsl());
            typeValueMap.put("usl", dto.getUsl());
            typeValueMap.put("spec", dto.getSpec());

            for (Field field : fields) {
                if (!field.isAnnotationPresent(FieldCode.class)) continue;

                FieldCode ann = field.getAnnotation(FieldCode.class);
                if (ann.no().equals(dto.getNo())) {
                    String rawValue = typeValueMap.get(ann.type());
                    if (rawValue != null && !rawValue.isEmpty()) {
                        try {
                            field.setAccessible(true);
                            field.set(obj, convertToFieldType(rawValue, field.getType()));
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("字段设置失败: " + field.getName(), e);
                        }
                    }
                }
            }
        }
    }



    // 数值允许的最小值和最大值
    private static final BigDecimal MIN_VALUE = new BigDecimal("-99999999");
    private static final BigDecimal MAX_VALUE = new BigDecimal("99999999");

    public static Object convertToFieldType(String val, Class<?> type) {
        try {
            // 空值直接返回默认值
            if (val == null || val.trim().isEmpty()) {
                return DefaultValueEnum.getDefault(type);
            }

            // 特殊处理数值型：先转 BigDecimal，统一做范围校验
            if (Number.class.isAssignableFrom(type) ||
                    type == int.class || type == long.class ||
                    type == float.class || type == double.class ||
                    type == BigDecimal.class) {

                BigDecimal decimal = new BigDecimal(val);

                // 范围校验
                if (decimal.compareTo(MIN_VALUE) < 0 || decimal.compareTo(MAX_VALUE) > 0) {
                    throw new IllegalArgumentException(
                            String.format("数值超出允许范围: %s (允许范围: %s ~ %s)", val, MIN_VALUE, MAX_VALUE)
                    );
                }

                // 类型转换 + 保留两位小数
                if (type == Integer.class || type == int.class) return decimal.intValue();
                if (type == Long.class || type == long.class) return decimal.longValue();
                if (type == Float.class || type == float.class)
                    return decimal.floatValue();
                if (type == Double.class || type == double.class)
                    return decimal.doubleValue();
                if (type == BigDecimal.class)
                    return decimal;
            }

            // 字符串类型
            if (type == String.class) return val;

            // 布尔类型：支持 "1" / "true" / "TRUE"
            if (type == Boolean.class || type == boolean.class)
                return val.equals("1") || val.equalsIgnoreCase("true");

        } catch (Exception e) {
            System.err.printf("⚠️ 类型转换失败：值=%s → %s，原因：%s%n", val, type.getSimpleName(), e.getMessage());
        }

        return DefaultValueEnum.getDefault(type);
    }
}