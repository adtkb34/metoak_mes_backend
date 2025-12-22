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

import static com.metoak.mes.common.result.ResultCodeEnum.Field_NOT_FOUND;

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
    private IMoCalibrationService moCalibrationService;

    @Autowired
    private IErrorDescriptionsService errorDescriptionsService;

    @GetMapping
    public Result getLatestCustomAttrKey(@RequestParam String productSn, @RequestParam String attrKey) {
        return moCalibrationService.getLatestCustomAttrKey(productSn, attrKey);
    }

    @GetMapping("latest")
    public Result getLatestCustomAttrKey2(@RequestParam String productSn, @RequestParam String attrKey) {
        return moCalibrationService.getLatestCustomAttrKey(productSn, attrKey);
    }



}
