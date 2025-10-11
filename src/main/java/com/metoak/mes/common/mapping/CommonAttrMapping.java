package com.metoak.mes.common.mapping;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CommonAttrMapping {

    public static final Map<String, String> FIELD_TO_FIELD = new HashMap<>();

    public static final Map<String, String> FIELD_TO_FIELD2 = new HashMap<>();

    static {
        FIELD_TO_FIELD.put("beamSn", "productSn");
        FIELD_TO_FIELD.put("cameraSn", "productSn");
        FIELD_TO_FIELD.put("cameraSN", "productSn");
        FIELD_TO_FIELD.put("CameraSN", "productSn");
        FIELD_TO_FIELD.put("batchNo", "productBatchNo");
        FIELD_TO_FIELD.put("errorCode", "errorNo");
        FIELD_TO_FIELD.put("ngReason", "error");
        FIELD_TO_FIELD.put("stationNum", "deviceNo");
        FIELD_TO_FIELD.put("station", "deviceNo");
        FIELD_TO_FIELD.put("operator", "operator");

        FIELD_TO_FIELD2.put("stage", "stage");
        FIELD_TO_FIELD2.put("position", "position");
        FIELD_TO_FIELD2.put("frequency", "frequency");
        FIELD_TO_FIELD2.put("block", "block");
    }

    public static void mapDtoFields(Object target, Object source, Map<String, String> fieldMap) {
        Class<?> sourceClass = source.getClass();

        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            String targetFieldName = entry.getKey();
            String sourceFieldName = entry.getValue();

            try {
                Field sourceField = sourceClass.getDeclaredField(sourceFieldName);
                sourceField.setAccessible(true);
                Object sourceVal = sourceField.get(source);

                if (sourceVal != null) {
                    ReflectionMapper.setField(target, targetFieldName, String.valueOf(sourceVal));
                }
            } catch (Exception e) {
                System.err.printf("⚠️ 映射失败：%s ← %s%n", targetFieldName, sourceFieldName);
                e.printStackTrace();
            }
        }
    }

    public static void mapEntityFieldsToDto(Object source, Object target, Map<String, String> fieldMap) {
        Class<?> sourceClass = source.getClass();

        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            String sourceFieldName = entry.getKey();
            String targetFieldName = entry.getValue();

            try {
                Field sourceField = sourceClass.getDeclaredField(sourceFieldName);
                sourceField.setAccessible(true);
                Object sourceVal = sourceField.get(source);

                if (sourceVal != null) {
                    Field targetField = target.getClass().getDeclaredField(targetFieldName);
                    targetField.setAccessible(true);
                    targetField.set(target, String.valueOf(sourceVal));
                }
            } catch (Exception e) {
                System.err.printf("⚠️ 反向映射失败：%s → %s%n", sourceFieldName, targetFieldName);
                e.printStackTrace();
            }
        }
    }
}

