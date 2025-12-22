package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.entity.MoCalibration;
import com.metoak.mes.mapper.MoCalibrationMapper;
import com.metoak.mes.service.IMoCalibrationService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.metoak.mes.common.result.ResultCodeEnum.Field_NOT_FOUND;
import static com.metoak.mes.common.result.ResultCodeEnum.RECORD_NOT_FOUND;

/**
 * <p>
 * 标定工具数据表 服务实现类
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@Service
public class MoCalibrationServiceImpl extends ServiceImpl<MoCalibrationMapper, MoCalibration> implements IMoCalibrationService {

    @Override
    public Result getLatestCustomAttrKey(String productSn, String attrKey) {
        LambdaQueryWrapper<MoCalibration> wrapper = new LambdaQueryWrapper<MoCalibration>()
                .eq(MoCalibration::getCameraSn, productSn)
                .orderByDesc(MoCalibration::getId)
                .last("LIMIT 1");
        MoCalibration lastRecord = getOne(wrapper);

        Map<String, List<String>> result = new HashMap<>();
        result.put("OK", new ArrayList<>());
        result.put("NG", new ArrayList<>());

        if (lastRecord != null) {
            try {
                // 动态获取字段值
                String fieldValue = getFieldValueByAttrKey(lastRecord, attrKey);

                if (lastRecord.getErrorCode() == 0) {
                    result.get("OK").add(fieldValue);
                } else {
                    result.get("NG").add(fieldValue);
                }
                return Result.ok(result);
            } catch (Exception e) {
                // 处理字段不存在的情况
                return Result.fail(Field_NOT_FOUND.getCode(), "Field not found: " + attrKey, result);
            }
        } else {
            return Result.fail(RECORD_NOT_FOUND, result);
        }
    }

    public String getFieldValueByAttrKey(MoCalibration record, String attrKey) throws Exception {
        attrKey = toCamelCase(attrKey);
        // 将attrKey转换为对应的getter方法名
        String methodName = "get" + attrKey.substring(0, 1).toUpperCase() + attrKey.substring(1);

        Method method = record.getClass().getMethod(methodName);
        Object value = method.invoke(record);

        return value != null ? value.toString() : null;
    }

    private String toCamelCase(String underscoreStr) {
        if (underscoreStr == null || underscoreStr.isEmpty()) {
            return underscoreStr;
        }

        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;

        for (int i = 0; i < underscoreStr.length(); i++) {
            char c = underscoreStr.charAt(i);

            if (c == '_') {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    result.append(Character.toUpperCase(c));
                    nextUpper = false;
                } else {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}
