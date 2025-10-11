package com.metoak.mes.common.util;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;

public class SortUtil {

    public static <T> void sortByFields(List<T> list, String... fieldNames) {
        Comparator<T> comparator = null;

        for (String field : fieldNames) {
            Comparator<T> fieldComparator = Comparator.comparing(obj -> {
                try {
                    Field f = obj.getClass().getDeclaredField(field);
                    f.setAccessible(true);
                    Object value = f.get(obj);
                    return (Comparable<Object>) value;
                } catch (Exception e) {
                    throw new RuntimeException("Error accessing field: " + field, e);
                }
            }, Comparator.nullsFirst(Comparator.naturalOrder()));

            comparator = (comparator == null) ? fieldComparator : comparator.thenComparing(fieldComparator);
        }

        list.sort(comparator);
    }
}