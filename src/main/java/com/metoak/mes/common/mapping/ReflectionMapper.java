package com.metoak.mes.common.mapping;

import com.metoak.mes.enums.DefaultValueEnum;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static com.metoak.mes.common.annotate.FieldCodeMapper.convertToFieldType;

public class ReflectionMapper {

    /**
     * 将字符串值设置到指定对象的字段上，自动转换类型
     */
    public static void setField(Object target, String fieldName, String value) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object converted = convertToFieldType(value, field.getType());
            field.set(target, converted);
        } catch (Exception e) {
            System.out.println(e);
            System.err.printf("⚠️ 字段赋值失败 [%s] = %s\n", fieldName, value);
        }
    }

    private static Object convert(String val, Class<?> type) {

        try {
            if (type == String.class) return val;
            if (type == Integer.class || type == int.class) return Integer.parseInt(val);
            if (type == Long.class || type == long.class) return Long.parseLong(val);
            if (type == Float.class || type == float.class) return Float.parseFloat(val);
            if (type == Double.class || type == double.class) return Double.parseDouble(val);
            if (type == BigDecimal.class) return new BigDecimal(val);
            if (type == Boolean.class || type == boolean.class) return val.equals("1") || val.equalsIgnoreCase("true");
        } catch (Exception e) {
            System.err.printf("⚠️ 类型转换失败：值=%s → %s%n", val, type.getSimpleName());
        }

        return DefaultValueEnum.getDefault(type);
    }


}