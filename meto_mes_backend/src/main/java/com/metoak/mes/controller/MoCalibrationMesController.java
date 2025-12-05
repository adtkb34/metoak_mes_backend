package com.metoak.mes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.entity.MoCalibration;
import com.metoak.mes.service.IErrorDescriptionsService;
import com.metoak.mes.service.IMoCalibrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.*;

/**
 * <p>
 * 标定工具数据表 前端控制器
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */

@RestController
@RequestMapping("/api/mes/v1/calibration")
public class MoCalibrationMesController {

    @Autowired
    private IMoCalibrationService service;

    @Autowired
    private IErrorDescriptionsService errorDescriptionsService;

    @GetMapping
    public Result getLatestCustomAttrKey(@RequestParam String productSn, @RequestParam String attrKey) {
        LambdaQueryWrapper<MoCalibration> wrapper = new LambdaQueryWrapper<MoCalibration>()
                .eq(productSn != null, MoCalibration::getCameraSn, productSn)
                .eq(MoCalibration::getErrorCode, 0)
                .orderByDesc(MoCalibration::getId)
                .last("LIMIT 1");
        MoCalibration lastRecord = service.getOne(wrapper);

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
            } catch (Exception e) {
                // 处理字段不存在的情况
                result.get("NG").add("Field not found: " + attrKey);
            }
        }

        return Result.ok(result);
    }


    @GetMapping("v1/latestSuccess/attrKey")
    public Result getV1LatestSuccessCustomAttrKey(@RequestParam String productSn, @RequestParam String attrKey) {
        LambdaQueryWrapper<MoCalibration> wrapper = new LambdaQueryWrapper<MoCalibration>()
                .eq(MoCalibration::getCameraSn, productSn)
                .eq(MoCalibration::getErrorCode, 0)
                .orderByDesc(MoCalibration::getId)
                .last("LIMIT 1");
        MoCalibration lastRecord = service.getOne(wrapper);

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
            } catch (Exception e) {
                // 处理字段不存在的情况
                result.get("NG").add("Field not found: " + attrKey);
            }
        }

        return Result.ok(result);
    }

    @GetMapping("v2/latestSuccess/attrKey")
    public Result getV2LatestSuccessCustomAttrKey(@RequestParam String productSn, @RequestParam String attrKey) {
        LambdaQueryWrapper<MoCalibration> wrapper = new LambdaQueryWrapper<MoCalibration>()
                .eq(MoCalibration::getCameraSn, productSn)
                .eq(MoCalibration::getErrorCode, 0)
                .orderByDesc(MoCalibration::getId)
                .last("LIMIT 1");
        MoCalibration lastRecord = service.getOne(wrapper);
        if (lastRecord != null) {
            try {
                // 动态获取字段值
                String fieldValue = getFieldValueByAttrKey(lastRecord, attrKey);
                return Result.ok(fieldValue);
            } catch (Exception e) {
                return Result.fail(2, "Field not found: " + attrKey);
            }
        }

        return Result.fail(1, null);
    }


    private String getFieldValueByAttrKey(MoCalibration record, String attrKey) throws Exception {
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
