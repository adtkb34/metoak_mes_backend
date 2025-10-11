package com.metoak.mes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.entity.ErrorDescriptions;
import com.metoak.mes.entity.MoAdjustFocus;
import com.metoak.mes.entity.MoAutoAdjustInfo;
import com.metoak.mes.entity.MoCalibration;
import com.metoak.mes.service.IErrorDescriptionsService;
import com.metoak.mes.service.IMoAdjustFocusService;
import com.metoak.mes.service.IMoAutoAdjustInfoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 调焦工具数据表 前端控制器
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@RestController
@RequestMapping("/api/v1/moAdjustFocus")
public class MoAdjustFocusController {
    @Autowired
    private IMoAdjustFocusService service;

    @Autowired
    private IErrorDescriptionsService errorDescriptionsService;

    @GetMapping("statisticsError")
    @Operation(summary = "统计标定错误信息")
    public Result statisticsError(@RequestParam(required = false) List<Integer> stationNos,
                                  @RequestParam(required = false) String serialNo,
                                  @RequestParam(required = false) Integer cameraPartTypeId,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        LambdaQueryWrapper<MoAdjustFocus> wrapper = new LambdaQueryWrapper<MoAdjustFocus>().
                in(stationNos!=null && !stationNos.isEmpty(), MoAdjustFocus::getStationNumber, stationNos).
                eq(serialNo!=null, MoAdjustFocus::getCameraSn, serialNo).
                ge(startTime!=null, MoAdjustFocus::getStartTime, startTime).
                lt(endTime!=null, MoAdjustFocus::getEndTime, endTime);
        Map<Integer, Integer> errorCodeMap = new HashMap<>();
        service.list(wrapper).forEach(entity -> {
            errorCodeMap.merge(entity.getErrorCode(), 1, Integer::sum);
        });
        LambdaQueryWrapper<ErrorDescriptions> calibration = new LambdaQueryWrapper<ErrorDescriptions>().eq(ErrorDescriptions::getProcedure, "MA");
        List<ErrorDescriptions> errorDescriptions = errorDescriptionsService.list(calibration);
        ArrayList<Map<String, String>> maps = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : errorCodeMap.entrySet()) {
            HashMap<String, String> stringStringHashMap = new HashMap<>();
            stringStringHashMap.put("code", entry.getKey().toString());
            if (entry.getKey().equals(0)) {
                stringStringHashMap.put("message", "成功");
            } else {
                Optional<ErrorDescriptions> first = errorDescriptions.stream().filter(item -> item.getCode().equals(entry.getKey().toString())).findFirst();
                first.ifPresent(descriptions -> stringStringHashMap.put("message", descriptions.getMessage()));
            }
            stringStringHashMap.put("qty", entry.getValue().toString());
            maps.add(stringStringHashMap);
        }
        return Result.ok(maps);
    }
}
