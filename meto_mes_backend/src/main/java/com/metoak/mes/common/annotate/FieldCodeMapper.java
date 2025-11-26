package com.metoak.mes.common.annotate;

import com.metoak.mes.dto.AttrKeyValDto;
import com.metoak.mes.enums.DefaultValueEnum;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class FieldCodeMapper {

    public static void applyAttrListToObject(Object obj, List<AttrKeyValDto> dtoList) {
        if (obj == null || dtoList == null) {
            return;
        }

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (AttrKeyValDto dto : dtoList) {
            Map<String, String> typeValueMap = new HashMap<>();
            typeValueMap.put("val", dto.getVal());
            typeValueMap.put("lsl", dto.getLsl());
            typeValueMap.put("usl", dto.getUsl());
            typeValueMap.put("spec", dto.getSpec());

            for (Field field : fields) {
                if (!field.isAnnotationPresent(FieldCode.class)) {
                    continue;
                }

                FieldCode ann = field.getAnnotation(FieldCode.class);
                if (!ann.no().equals(dto.getNo())) {
                    continue;
                }

                String rawValue = typeValueMap.get(ann.type());
                if (rawValue == null || rawValue.isEmpty()) {
                    continue;
                }

                try {
                    field.setAccessible(true);
                    field.set(obj, convertToFieldType(rawValue, field.getType()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("字段设置失败: " + field.getName(), e);
                }
            }
        }
    }

//    public static List<AttrKeyValDto> extractAttrListFromObject(Object obj) {
//        return extractAttrListFromObject(obj, null);
//    }
    public static Map<String, Field> buildFieldMap(Class<?> clazz) {
        Map<String, Field> map = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            String key = field.getName()
                    .toLowerCase()
                    .replace("_", ""); // 去掉下划线

            map.put(key, field);
        }

        return map;
    }



    public static List<AttrKeyValDto> extractAttrListFromObject(Object obj, Set<String> attrKeyFilter_) {//
        Set<String> attrKeyFilter = attrKeyFilter_.stream()
                .map(s -> s.replace("_", ""))
                .collect(Collectors.toSet());

        if (obj == null) {
            return List.of();
        }

        Map<String, AttrKeyValDto> grouped = new LinkedHashMap<>();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
//        for (String s : attrKeyFilter) {
//            Field field = fieldMap.get(s);
            if (!field.isAnnotationPresent(FieldCode.class)) {
                continue;
            }
            if (attrKeyFilter != null) {

                if (!attrKeyFilter.contains(field.getName().toLowerCase())) {
                    continue;
                }

            }
            FieldCode annotation = field.getAnnotation(FieldCode.class);

            AttrKeyValDto dto = grouped.computeIfAbsent(annotation.no(), no -> {
                AttrKeyValDto attr = new AttrKeyValDto();
                attr.setNo(no);
                attr.setKey(resolveLabel(field, annotation));
                if (annotation.index() != Integer.MIN_VALUE) {
                    attr.setIdx(String.valueOf(annotation.index()));
                }
                return attr;
            });

            Object raw = readField(field, obj);
            if (raw != null) {
                String value = stringify(raw);
                switch (annotation.type()) {
                    case "val" -> dto.setVal(value);
                    case "lsl" -> dto.setLsl(value);
                    case "usl" -> dto.setUsl(value);
                    case "spec" -> dto.setSpec(value);
                    case "result" -> dto.setResult(value);
                    default -> {
                        // ignore unsupported type
                    }
                }
            }

            if (annotation.hasPosition()) {
                dto.setPosition(readPosition(obj));
            } else if (annotation.position() != Integer.MIN_VALUE) {
                dto.setPosition(String.valueOf(annotation.position()));
            } else if (dto.getPosition() == null) {
                dto.setPosition(null);
            }
        }
        return new ArrayList<>(grouped.values());
    }

    public static String resolveLabel(Field field) {
        return resolveLabel(field, field.getAnnotation(FieldCode.class));
    }

    public static String resolveLabel(Field field, FieldCode annotation) {
        if (annotation == null) {
            return field.getName();
        }

        if (hasText(annotation.label())) {
            return annotation.label().trim();
        }

        if (hasText(annotation.name())) {
            return annotation.name().trim();
        }

        return field.getName();
    }

    private static Object readField(Field field, Object obj) {
        try {
            field.setAccessible(true);
            return field.get(obj);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    private static String readPosition(Object obj) {
        try {
            Field positionField = obj.getClass().getDeclaredField("position");
            positionField.setAccessible(true);
            Object position = positionField.get(obj);
            return position == null ? null : stringify(position);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }

    private static String stringify(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal decimal) {
            return decimal.stripTrailingZeros().toPlainString();
        }
        return String.valueOf(value);
    }

    // 数值允许的最小值和最大值
    private static final BigDecimal MIN_VALUE = new BigDecimal("-999999999999999");
    private static final BigDecimal MAX_VALUE = new BigDecimal("999999999999999");

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
                    type == BigDecimal.class||
                    type == LocalDateTime.class) {

                BigDecimal decimal = new BigDecimal(val);

                // 范围校验
                if (decimal.compareTo(MIN_VALUE) < 0 || decimal.compareTo(MAX_VALUE) > 0) {
                    throw new IllegalArgumentException(
                            String.format("数值超出允许范围: %s (允许范围: %s ~ %s)", val, MIN_VALUE, MAX_VALUE)
                    );
                }
                if (type == LocalDateTime.class) return LocalDateTime.ofInstant(Instant.ofEpochMilli(decimal.longValue()), ZoneId.systemDefault());;
                if (type == Byte.class || type == byte.class) return decimal.byteValue();
                if (type == Integer.class || type == int.class) return decimal.intValue();
                if (type == Long.class || type == long.class) return decimal.longValue();
                if (type == Float.class || type == float.class) return decimal.floatValue();
                if (type == Double.class || type == double.class) return decimal.doubleValue();
                if (type == BigDecimal.class) return decimal;
            }

            if (type == String.class) {
                return val;
            }

            if (type == Boolean.class || type == boolean.class) {
                return val.equals("1") || val.equalsIgnoreCase("true");
            }

        } catch (Exception e) {
            System.err.printf("⚠️ 类型转换失败：值=%s → %s，原因：%s%n", val, type.getSimpleName(), e.getMessage());
        }

        return DefaultValueEnum.getDefault(type);
    }

    private static boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
