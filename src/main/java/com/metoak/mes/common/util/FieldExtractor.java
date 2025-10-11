package com.metoak.mes.common.util;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.metoak.mes.common.MOException;
import com.metoak.mes.common.annotate.FieldCode;
import com.metoak.mes.common.mapping.ProcessMappingRegistry;
import com.metoak.mes.dto.OptionDto;
import com.metoak.mes.enums.DefaultValueEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
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
                String name = annotation.name();
                // 避免重复添加
                if (seenNos.add(no)) {
                    result.add(new OptionDto(no, name));
                }
            }
        }

        return result;
    }
//, LocalDateTime startTime, LocalDateTime endTime, int limit
    public  <T> List<Object> getFieldValuesByNo(ProcessMappingRegistry.ProcessMapping processMapping,
                                                String no, String start, String end, Integer limit) {
        String fieldName = null;

        Class<?> entityClass = processMapping.getEntityClass();
        Class<?> serviceClass = processMapping.getServiceClass();
        // 反射获取字段名
        for (Field field : entityClass.getDeclaredFields()) {
            FieldCode annotation = field.getAnnotation(FieldCode.class);
            if (annotation != null && "val".equals(annotation.type()) && annotation.no().equals(no)) {
                fieldName = field.getName();
                break;
            }
        }

        if (fieldName == null) {
            throw new IllegalArgumentException("参数编号 " + no + " 未在 " + entityClass.getSimpleName() + " 中找到对应字段");
        }

        // 构建查询（以 MyBatis-Plus 为例）
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.last("limit " + limit).orderByDesc("add_time");
        if (start != null && end != null) {
            queryWrapper.between("add_time", start, end);
        }

        // 这里以 mapper 为例
        Object service = applicationContext.getBean(processMapping.getServiceClass());
        Method listMethod = null;
        try {
            listMethod = service.getClass().getMethod("list", Wrapper.class);
            @SuppressWarnings("unchecked")
            List<Object> entityList = (List<Object>) listMethod.invoke(service, queryWrapper);
            List<Object> values = new ArrayList<>();
            for (Object entity : entityList) {
                Field f = entity.getClass().getDeclaredField(fieldName);
                f.setAccessible(true);
                Object val = f.get(entity);

// 获取该字段的默认值
                Object defaultVal = DefaultValueEnum.getDefault(f.getType());
                if (val != null && defaultVal != null) {
                    if (val instanceof Number && defaultVal instanceof Number) {
                        double v = ((Number) val).doubleValue();
                        double d = ((Number) defaultVal).doubleValue();

                        if (Math.abs(v - d) / Math.max(Math.abs(v), 1e-10) > 1e-6) {  // 避免除以 0
                            values.add(val);
                        }
                    } else if (!val.equals(defaultVal)) {
                        values.add(val); // 非数值类型时，比较不相等才添加
                    }
                }
            }
            return values;
        } catch (Exception e) {

            throw new MOException("", -1);
        }

    }
}