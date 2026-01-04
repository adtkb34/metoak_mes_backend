package com.metoak.mes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.dto.ErrorStatisticsDto;
import com.metoak.mes.entity.ErrorDescriptions;
import com.metoak.mes.entity.MoCalibration;
import com.metoak.mes.service.IErrorDescriptionsService;
import com.metoak.mes.service.IMoCalibrationService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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
public class MoCalibrationController {

    @Autowired
    private IMoCalibrationService service;

    @Autowired
    private IErrorDescriptionsService errorDescriptionsService;


    @GetMapping("list")
    @Operation(summary = "获取校准信息")
    public Result list(@RequestParam(required = false) String cameraSn,
                       @RequestParam(required = false) Integer stationNumber,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        LambdaQueryWrapper<MoCalibration> wrapper = new LambdaQueryWrapper<MoCalibration>().
                eq(cameraSn!=null, MoCalibration::getCameraSn, cameraSn).eq(stationNumber!=null, MoCalibration::getStationNumber, stationNumber).
                le(startTime!=null, MoCalibration::getStartTime, startTime).ge(startTime!=null, MoCalibration::getEndTime, endTime);

        List<MoCalibration> list = service.list(wrapper);
        return Result.ok(list);
    }

    @GetMapping("cameraSnlist")
    @Operation(summary = "获取相机序列号信息")
    public Result cameraSnlist(@RequestParam(required = false) Integer stationNumber,
                               @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
                               @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        LambdaQueryWrapper<MoCalibration> wrapper = new LambdaQueryWrapper<MoCalibration>().
                eq(stationNumber!=null, MoCalibration::getStationNumber, stationNumber).
                le(startTime!=null, MoCalibration::getStartTime, startTime).
                ge(startTime!=null, MoCalibration::getEndTime, endTime);
        List<String> list = service.list(wrapper).stream().map(MoCalibration::getCameraSn).collect(Collectors.toList());
        return Result.ok(list);
    }

    @GetMapping("statisticsError")
    @Operation(summary = "统计标定错误信息")
    public Result statisticsError(@RequestParam(required = false) List<Integer> stationNos,
                                  @RequestParam(required = false) String serialNo,
                                  @RequestParam(required = false) Integer cameraPartTypeId,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        LambdaQueryWrapper<MoCalibration> wrapper = new LambdaQueryWrapper<MoCalibration>().
                in(stationNos!=null && !stationNos.isEmpty(), MoCalibration::getStationNumber, stationNos).
                eq(serialNo!=null, MoCalibration::getCameraSn, serialNo).
                ge(startTime!=null, MoCalibration::getStartTime, startTime).
                le(endTime!=null, MoCalibration::getEndTime, endTime);
        Map<Integer, Integer> errorCodeMap = new HashMap<>();

        ArrayList<LocalDateTime> localDateTimes = new ArrayList<>();
        LocalDateTime timeRecord = startTime;
        while (timeRecord.isBefore(endTime)) {
            localDateTimes.add(timeRecord);
            timeRecord = timeRecord.plus(2, ChronoUnit.HOURS);
        }
        service.list(wrapper).forEach(entity -> {
            errorCodeMap.merge(entity.getErrorCode(), 1, Integer::sum);
        });

        LambdaQueryWrapper<ErrorDescriptions> calibration = new LambdaQueryWrapper<ErrorDescriptions>().eq(ErrorDescriptions::getProcedure, "calibration");
        List<ErrorDescriptions> errorDescriptions = errorDescriptionsService.list(calibration);
        ArrayList<Map<String, String>> maps = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : errorCodeMap.entrySet()) {
            HashMap<String, String> stringStringHashMap = new HashMap<>();
            stringStringHashMap.put("code", entry.getKey().toString());
            if (entry.getKey().equals(0)) {
                stringStringHashMap.put("message", "成功");
            } else if (entry.getKey().equals(11026)) {
                stringStringHashMap.put("message", "自检失败");
            } else {
                Optional<ErrorDescriptions> first = errorDescriptions.stream().filter(item -> item.getCode().equals(entry.getKey().toString().substring(1, entry.getKey().toString().length()))).findFirst();
                first.ifPresent(descriptions -> stringStringHashMap.put("message", descriptions.getMessage()));
            }
            stringStringHashMap.put("qty", entry.getValue().toString());
            maps.add(stringStringHashMap);
        }
        return Result.ok(maps);
    }

    @GetMapping("statisticsError4SequenceDiagram")
    @Operation(summary = "统计标定错误信息")
    public Result statisticsError4SequenceDiagram(@RequestParam(required = false) List<Integer> stationNos,
                                  @RequestParam(required = false) String serialNo,
                                  @RequestParam(required = false) Integer cameraPartTypeId,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        LambdaQueryWrapper<MoCalibration> wrapper = new LambdaQueryWrapper<MoCalibration>().
                in(stationNos!=null && !stationNos.isEmpty(), MoCalibration::getStationNumber, stationNos).
                eq(serialNo!=null, MoCalibration::getCameraSn, serialNo).
                ge(startTime!=null, MoCalibration::getStartTime, startTime).
                le(endTime!=null, MoCalibration::getEndTime, endTime);
        Map<Integer, ErrorStatisticsDto> errorStatisticsDtos = new HashMap<>();

        ArrayList<LocalDateTime> localDateTimes = new ArrayList<>();
        LocalDateTime timeRecord = startTime;
        while (timeRecord.isBefore(endTime)) {
            localDateTimes.add(timeRecord);
            timeRecord = timeRecord.plus(2, ChronoUnit.HOURS);
        }

        service.list(wrapper).forEach(entity -> {
            ErrorStatisticsDto errorStatisticsDto;
            if (!errorStatisticsDtos.containsKey(entity.getErrorCode())) {
                errorStatisticsDto = new ErrorStatisticsDto();
                errorStatisticsDto.setTimes(localDateTimes);
                errorStatisticsDto.setCounts(new ArrayList<>(Collections.nCopies(localDateTimes.size(), 0)));
                errorStatisticsDto.setErrorNum(entity.getErrorCode());
                errorStatisticsDtos.put(entity.getErrorCode(), errorStatisticsDto);
            } else {
                errorStatisticsDto = errorStatisticsDtos.get(entity.getErrorCode());
            }
            Integer count = 0;
            List<Integer> counts = errorStatisticsDto.getCounts();
            for (LocalDateTime time : errorStatisticsDto.getTimes()) {
                count += 1;
                if (entity.getEndTime().isBefore(time)) {
                    counts.set(count - 1, counts.get(count - 1) + 1);
                }
            }
            errorStatisticsDto.setCounts(counts);
        });

        LambdaQueryWrapper<ErrorDescriptions> calibration = new LambdaQueryWrapper<ErrorDescriptions>().eq(ErrorDescriptions::getProcedure, "calibration");
        List<ErrorDescriptions> errorDescriptions = errorDescriptionsService.list(calibration);
        for (ErrorStatisticsDto errorStatisticsDto : errorStatisticsDtos.values()) {
            if (errorStatisticsDto.getErrorNum().equals(0)) {
                errorStatisticsDto.setError("成功");
            } else {
                Optional<ErrorDescriptions> first = errorDescriptions.stream().filter(item -> item.getCode().equals(errorStatisticsDto.getErrorNum().toString().substring(1, errorStatisticsDto.getErrorNum().toString().length()))).findFirst();
                first.ifPresent(descriptions -> errorStatisticsDto.setError(descriptions.getMessage()));
            }
        }
        return Result.ok(errorStatisticsDtos);
    }

    @GetMapping("statisticsSelfCheckError")
    @Operation(summary = "统计标定自检错误信息")
    public Result statisticsSelfCheckError(@RequestParam(required = false) List<Integer> stationNos,
                                  @RequestParam(required = false) String serialNo,
                                  @RequestParam(required = false) Integer cameraPartTypeId,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        LambdaQueryWrapper<MoCalibration> wrapper = new LambdaQueryWrapper<MoCalibration>().
                in(stationNos!=null && !stationNos.isEmpty(), MoCalibration::getStationNumber, stationNos).
                eq(serialNo!=null, MoCalibration::getCameraSn, serialNo).
                ge(startTime!=null, MoCalibration::getStartTime, startTime).
                le(endTime!=null, MoCalibration::getEndTime, endTime).
                eq(MoCalibration::getErrorCode, 11026);
        Map<String, Integer> errorCodeMap = new HashMap<>();
        List<MoCalibration> list = service.list(wrapper);
        list.forEach(entity -> {
            if (entity.getSelfCheckErrcode() != null) {
                errorCodeMap.merge(Long.toHexString(entity.getSelfCheckErrcode()), 1, Integer::sum);
            }
        });
        ArrayList<Map<String, String>> maps = new ArrayList<>();
        for (var entry : errorCodeMap.entrySet()) {
            HashMap<String, String> stringStringHashMap = new HashMap<>();
            stringStringHashMap.put("code", StringUtils.leftPad(entry.getKey(), 7, '0'));
            Optional<MoCalibration> first = list.stream().filter(item -> item.getSelfCheckErrcode() != null && Long.toHexString(item.getSelfCheckErrcode()).equals(entry.getKey().toString())).findFirst();
            first.ifPresent(descriptions -> stringStringHashMap.put("message", descriptions.getSelfCheckErrorDesc()));
            stringStringHashMap.put("qty", entry.getValue().toString());
            maps.add(stringStringHashMap);
        }
        return Result.ok(maps);
    }

    @GetMapping("listError")
    @Operation(summary = "列举标定错误信息")
    public Result listError(@RequestParam(required = false) List<Integer> stationNos,
                                  @RequestParam(required = false) Integer serialNo,
                                  @RequestParam(required = false) Integer cameraPartTypeId,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        LambdaQueryWrapper<MoCalibration> wrapper = new LambdaQueryWrapper<MoCalibration>().
                in(stationNos!=null && !stationNos.isEmpty(), MoCalibration::getStationNumber, stationNos).
                eq(serialNo!=null, MoCalibration::getCameraSn, serialNo).
                ge(startTime!=null, MoCalibration::getStartTime, startTime).
                le(endTime!=null, MoCalibration::getEndTime, endTime);
        return Result.ok(service.list(wrapper));
    }

}
