package com.metoak.mes.common.mapping.bak;

import com.metoak.mes.dto.AttrKeyValDto;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class DtoEntityMapper {

    /**
     * 通用方法：根据 AttrKeyValDto 列表，将对应的 val 设置到目标对象指定字段上
     *
     * @param dtoList      输入数据
     * @param entity       要赋值的实体对象
     * @param noToFieldMap 编号（如 "003"）到字段名（如 "tMtf"）的映射
     * @param <T>          实体类类型
     */
    public static <T> void applyDtoToEntity(List<AttrKeyValDto> dtoList, T entity, Map<String, Map<String, String>> noToFieldMap) {
        if (dtoList == null || entity == null || noToFieldMap == null) return;

        Class<?> clazz = entity.getClass();
        for (String key : List.of("val", "spec", "usl", "lsl")) {
            Map<String, String> subMap = noToFieldMap.get(key);
            if (subMap == null) {
                continue;
            }

            for (AttrKeyValDto dto : dtoList) {
                String fieldName = subMap.get(dto.getNo());
                if (fieldName != null) {
                    try {
                        Field field = clazz.getDeclaredField(fieldName);
                        field.setAccessible(true);

                        String rawVal = switch (key) {
                            case "val" -> dto.getVal();
                            case "spec" -> dto.getSpec();
                            case "usl" -> dto.getUsl();
                            case "lsl" -> dto.getLsl();
                            default -> null;
                        };

                        Object convertedVal = convert(rawVal, field.getType());
                        field.set(entity, convertedVal);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        System.err.println("字段赋值失败: " + fieldName + " ← " + key + ":" + dto.getNo());
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    private static Object convert(String val, Class<?> targetType) {
        if (val == null || val.equals("-123456")) return getDefaultValue(targetType); // 默认值标记

        try {
            if (targetType == String.class) return val;
            if (targetType == Integer.class || targetType == int.class) return Integer.valueOf(val);
            if (targetType == Long.class || targetType == long.class) return Long.valueOf(val);
            if (targetType == Double.class || targetType == double.class) return Double.valueOf(val);
            if (targetType == Float.class || targetType == float.class) return Float.valueOf(val);
            if (targetType == BigDecimal.class) return new BigDecimal(val);
            if (targetType == Boolean.class || targetType == boolean.class)
                return val.equalsIgnoreCase("true") || val.equals("1");
            if (targetType == LocalDateTime.class)
                return LocalDateTime.parse(val); // 可改格式
        } catch (Exception e) {
            System.err.printf("⚠️ 类型转换失败：值=%s → %s，将使用奇异值\n", val, targetType.getSimpleName());
        }

        return getDefaultValue(targetType);
    }

    private static Object getDefaultValue(Class<?> type) {
        if (type == String.class) return "-999999";
        if (type == Integer.class || type == int.class) return -999999;
        if (type == Long.class || type == long.class) return -999999L;
        if (type == Float.class || type == float.class) return -999999.9f;
        if (type == Double.class || type == double.class) return -999999.999d;
        if (type == BigDecimal.class) return new BigDecimal("-999999.999");
        if (type == Boolean.class || type == boolean.class) return false;
        if (type == LocalDateTime.class) return LocalDateTime.of(1900, 1, 1, 0, 0);

        return null;
    }
}