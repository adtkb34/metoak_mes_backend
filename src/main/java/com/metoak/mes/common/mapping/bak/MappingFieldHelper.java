package com.metoak.mes.common.mapping.bak;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class MappingFieldHelper {

    public static Map<String, String> buildNoToFieldMapFromConstants(Class<?> constantClass) {
        Map<String, String> map = new HashMap<>();
        for (Field field : constantClass.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())) {
                try {
                    String no = (String) field.get(null); // e.g. "003"
                    map.put(no, toCamelCase(field.getName()));
                } catch (IllegalAccessException ignored) {}
            }
        }
        return map;
    }

    private static String toCamelCase(String s) {
        String[] parts = s.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            sb.append(Character.toUpperCase(parts[i].charAt(0)))
              .append(parts[i].substring(1));
        }
        return sb.toString();
    }
}