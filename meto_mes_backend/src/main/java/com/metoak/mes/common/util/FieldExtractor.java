package com.metoak.mes.common.util;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.metoak.mes.common.MOException;
import com.metoak.mes.common.annotate.FieldCode;
import com.metoak.mes.common.annotate.FieldCodeMapper;
import com.metoak.mes.common.mapping.ProcessMappingRegistry;
import com.metoak.mes.dto.OptionDto;
import com.metoak.mes.enums.DefaultValueEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class FieldExtractor {

    @Autowired
    private ApplicationContext applicationContext;

    public static List<OptionDto> extractValFields(Class<?> clazz) {
        List<OptionDto> result = new ArrayList<>();
        Set<String> seenNos = new HashSet<>(); // 避免重复编号

        for (Field field : clazz.getDeclaredFields()) {
            FieldCode annotation = field.getAnnotation(FieldCode.class);
            if (annotation != null && "val".equals(annotation.type())) {
                String no = annotation.no();
                String name = FieldCodeMapper.resolveLabel(field, annotation);
                // 避免重复添加
                if (seenNos.add(no)) {
                    result.add(new OptionDto(no, name));
                }
            }
        }

        return result;
    }
//, LocalDateTime startTime, LocalDateTime endTime, int limit
public <T> List<Object> getFieldValuesByNo(ProcessMappingRegistry.ProcessMapping processMapping,
                                           String no, String start, String end, Integer limit) {
    String fieldName = null;
    String columnName = null;

    Class<?> entityClass = processMapping.getEntityClass();
    Class<?> serviceClass = processMapping.getServiceClass();

    // 反射获取字段名
    for (Field field : entityClass.getDeclaredFields()) {
        FieldCode annotation = field.getAnnotation(FieldCode.class);
        if (annotation != null && "val".equals(annotation.type()) && annotation.no().equals(no)) {
            fieldName = field.getName();
            // 驼峰命名转下划线命名
            columnName = camelToUnderline(fieldName);
            break;
        }
    }

    if (fieldName == null) {
        throw new IllegalArgumentException("参数编号 " + no + " 未在 " + entityClass.getSimpleName() + " 中找到对应字段");
    }

    // 构建查询条件
    QueryWrapper<T> queryWrapper = new QueryWrapper<>();

    // 使用数据库列名而不是Java字段名
    queryWrapper.isNotNull(columnName);

    // 添加时间范围条件
    if (start != null && end != null) {
        queryWrapper.between("add_time", start, end);
    }

    // 排序和限制
    queryWrapper.orderByDesc("add_time")
            .last("LIMIT " + (limit * 10));

    Object service = null;
    try {
        // 获取 Service Bean
        service = applicationContext.getBean(serviceClass);

        // 获取 list 方法
        Method listMethod = serviceClass.getMethod("list", Wrapper.class);

        // 执行查询
        @SuppressWarnings("unchecked")
        List<T> entityList = (List<T>) listMethod.invoke(service, queryWrapper);

        List<Object> values = new ArrayList<>();
        for (T entity : entityList) {
            Field f = entity.getClass().getDeclaredField(fieldName); // 这里仍然使用Java字段名
            f.setAccessible(true);
            Object val = f.get(entity);

            // 获取该字段的默认值
            Object defaultVal = DefaultValueEnum.getDefault(f.getType());
            if (val != null && defaultVal != null) {
                if (val instanceof Number && defaultVal instanceof Number) {
                    // 修正这里的语法错误
                    double v = ((Number) val).doubleValue();
                    double d = ((Number) defaultVal).doubleValue();

                    if (Math.abs(v - d) / Math.max(Math.abs(v), 1e-10) > 1e-6) {
                        values.add(val);
                    }
                } else if (!val.equals(defaultVal)) {
                    values.add(val);
                }
            } else if (val != null && defaultVal == null) {
                values.add(val);
            }
        }
        return values.subList(0, Math.min(limit, values.size()));

    } catch (Exception e) {
        Throwable cause = e;
        if (e instanceof InvocationTargetException) {
            cause = ((InvocationTargetException) e).getTargetException();
        }

        String errorMsg = String.format(
                "获取字段值失败: 实体类=%s, 服务类=%s, 字段名=%s, 列名=%s, 错误=%s",
                entityClass.getSimpleName(),
                serviceClass.getSimpleName(),
                fieldName,
                columnName,
                cause.getMessage()
        );

        cause.printStackTrace();
        throw new MOException(errorMsg, -1);
    }
}

    // 驼峰命名转下划线命名工具方法
    private String camelToUnderline(String camelCase) {
        if (camelCase == null || camelCase.isEmpty()) {
            return camelCase;
        }

        StringBuilder result = new StringBuilder();
        result.append(Character.toLowerCase(camelCase.charAt(0)));

        for (int i = 1; i < camelCase.length(); i++) {
            char c = camelCase.charAt(i);
            if (Character.isUpperCase(c)) {
                result.append('_').append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}