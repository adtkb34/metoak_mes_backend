package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoak.mes.common.annotate.FieldCode;
import com.metoak.mes.common.annotate.FieldCodeMapper;
import com.metoak.mes.common.mapping.ProcessMappingRegistry;
import com.metoak.mes.entity.MoStepAttrKeys;
import com.metoak.mes.mapper.MoStepAttrKeysMapper;
import com.metoak.mes.service.IMoStepAttrKeysService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kevin
 * @since 2025-09-15 17:42:42
 */
@Service
public class MoStepAttrKeysServiceImpl extends ServiceImpl<MoStepAttrKeysMapper, MoStepAttrKeys> implements IMoStepAttrKeysService {

    @Override
    public Map<String, String> getByStepTypeNo(String stepTypeNo) {
        LambdaQueryWrapper<MoStepAttrKeys> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoStepAttrKeys::getStepTypeNo, stepTypeNo);
        return list(wrapper).stream().collect(Collectors.toMap(
                moStepAttrKeys -> moStepAttrKeys.getAttrKey().toLowerCase(), // 转换为小写
                MoStepAttrKeys::getAttrNo,
                (existingValue, newValue) -> existingValue // 合并函数，取旧值
        ));
    }

    @Override
    public String getLabel(String stepTypeNo, String attrNo) {
        if (!StringUtils.hasText(stepTypeNo) || !StringUtils.hasText(attrNo)) {
            return null;
        }

        ProcessMappingRegistry.ProcessMapping mapping = ProcessMappingRegistry.get(stepTypeNo.trim());
        if (mapping == null || mapping.getEntityClass() == null) {
            return null;
        }

        String normalized = attrNo.trim();
        String normalizedPadded = normalizeAttrNo(normalized);
        String fallback = null;

        for (Field field : mapping.getEntityClass().getDeclaredFields()) {
            FieldCode fieldCode = field.getAnnotation(FieldCode.class);
            if (fieldCode == null) {
                continue;
            }

            String code = fieldCode.no();
            if (!normalized.equals(code) && (normalizedPadded == null || !normalizedPadded.equals(code))) {
                continue;
            }

            String candidate = FieldCodeMapper.resolveLabel(field, fieldCode);
            if ("val".equalsIgnoreCase(fieldCode.type())) {
                return candidate;
            }

            if (fallback == null) {
                fallback = candidate;
            }
        }

        return fallback;
    }

    private String normalizeAttrNo(String attrNo) {
        if (!attrNo.matches("\\d+")) {
            return null;
        }
        try {
            int value = Integer.parseInt(attrNo);
            return String.format("%03d", value);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
