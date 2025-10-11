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
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@RestController
@RequestMapping("/api/v1/moAutoAdjustInfo")
public class MoAutoAdjustInfoController {

    @Autowired
    private IMoAutoAdjustInfoService service;

    @Autowired
    private IErrorDescriptionsService errorDescriptionsService;

    @GetMapping("statisticsError")
    @Operation(summary = "统计标定错误信息")
    public Result statisticsError(@RequestParam(required = false) List<Integer> stationNos,
                                  @RequestParam(required = false) String serialNo,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        LambdaQueryWrapper<MoAutoAdjustInfo> wrapper = new LambdaQueryWrapper<MoAutoAdjustInfo>()
                .in(stationNos != null && !stationNos.isEmpty(), MoAutoAdjustInfo::getStationNum, stationNos)
                .eq(serialNo != null, MoAutoAdjustInfo::getBeamSn, serialNo)
                .ge(startTime != null, MoAutoAdjustInfo::getOperationTime, startTime)
                .lt(endTime != null, MoAutoAdjustInfo::getOperationTime, endTime);

        Map<Integer, Integer> errorCodeMap = new ConcurrentHashMap<>();
        service.list(wrapper).forEach(entity -> {
            errorCodeMap.compute(entity.getErrorCode(), (code, count) -> (count == null) ? 1 : count + 1);
        });

        LambdaQueryWrapper<ErrorDescriptions> calibration = new LambdaQueryWrapper<ErrorDescriptions>().eq(ErrorDescriptions::getProcedure, "AA");
        List<ErrorDescriptions> errorDescriptions = errorDescriptionsService.list(calibration);
        Map<String, ErrorDescriptions> errorDescriptionMap = errorDescriptions.stream()
                .collect(Collectors.toMap(ErrorDescriptions::getCode, Function.identity()));

        ArrayList<Map<String, String>> maps = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : errorCodeMap.entrySet()) {
            HashMap<String, String> stringStringHashMap = new HashMap<>();
            stringStringHashMap.put("code", entry.getKey().toString());
            if (entry.getKey().equals(0)) {
                stringStringHashMap.put("message", "成功");
            } else {
                ErrorDescriptions descriptions = errorDescriptionMap.get(entry.getKey().toString());
                if (descriptions != null) {
                    stringStringHashMap.put("message", descriptions.getMessage());
                }
            }
            stringStringHashMap.put("qty", entry.getValue().toString());
            maps.add(stringStringHashMap);
        }
        return Result.ok(maps);
    }
}
