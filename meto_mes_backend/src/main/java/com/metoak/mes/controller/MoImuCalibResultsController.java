package com.metoak.mes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.entity.MoCalibration;
import com.metoak.mes.entity.MoImuCalibResults;
import com.metoak.mes.service.IMoCalibrationService;
import com.metoak.mes.service.IMoImuCalibResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kevin
 * @since 2025-07-01 19:21:55
 */
@RestController
@RequestMapping("/api/mes/v1/moImuCalibResults")
public class MoImuCalibResultsController {

    @Autowired
    private IMoImuCalibResultsService moImuCalibResultsService;

    @Autowired
    private IMoCalibrationService moCalibrationService;

    @GetMapping
    public Result getLast(@RequestParam String productSn) {
        LambdaQueryWrapper<MoImuCalibResults> wrapper = new LambdaQueryWrapper<MoImuCalibResults>()
                .eq(productSn != null, MoImuCalibResults::getSn, productSn)
                .orderByDesc(MoImuCalibResults::getCreateTime)
                .last("LIMIT 1");
        MoImuCalibResults lastRecord = moImuCalibResultsService.getOne(wrapper);
        if (lastRecord == null) {
            return Result.ok();
        }

        LambdaQueryWrapper<MoCalibration> wrapper2 = new LambdaQueryWrapper<MoCalibration>()
                .eq(MoCalibration::getId, lastRecord.getMoCalibrationId());
        MoCalibration calibLastRecord = moCalibrationService.getOne(wrapper2);
        if (calibLastRecord == null) {
            return Result.ok();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("root", lastRecord.getRoot());
        result.put("relative_path_yml_imu", lastRecord.getRelativePathYmlImu());
        result.put("relative_path_yml_imucam", lastRecord.getRelativePathYmlImucam());
        result.put("yml_filename", calibLastRecord.getYmlFilename());

        return Result.ok(result);
    }

    @GetMapping("latestSuccess")
    public Result getLatestSuccess(@RequestParam String productSn) {
        LambdaQueryWrapper<MoImuCalibResults> wrapper = new LambdaQueryWrapper<MoImuCalibResults>()
                .eq(MoImuCalibResults::getSn, productSn)
                .eq(MoImuCalibResults::getErrorCode, 0)
                .orderByDesc(MoImuCalibResults::getCreateTime)
                .last("LIMIT 1");
        MoImuCalibResults lastRecord = moImuCalibResultsService.getOne(wrapper);
        if (lastRecord == null) {
            return Result.ok();
        }

        LambdaQueryWrapper<MoCalibration> wrapper2 = new LambdaQueryWrapper<MoCalibration>()
                .eq(MoCalibration::getId, lastRecord.getMoCalibrationId());
        MoCalibration calibLastRecord = moCalibrationService.getOne(wrapper2);
        if (calibLastRecord == null) {
            return Result.ok();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("root", lastRecord.getRoot());
        result.put("relative_path_yml_imu", lastRecord.getRelativePathYmlImu());
        result.put("relative_path_yml_imucam", lastRecord.getRelativePathYmlImucam());
        result.put("yml_filename", calibLastRecord.getYmlFilename());

        return Result.ok(result);
    }


}
