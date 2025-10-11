package com.metoak.mes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.util.StatisticsCalculator;
import com.metoak.mes.dto.CalibresultLeftCenterOffsetOfXAndYDto;
import com.metoak.mes.entity.Calibresult;
import com.metoak.mes.entity.MoCalibration;
import com.metoak.mes.service.ICalibresultService;
import com.metoak.mes.service.IMoCalibrationService;
import com.metoak.mes.service.impl.CalibresultServiceImpl;
import com.metoak.mes.vo.CalibresultstatisticsLeftCenterOffsetOfXAndYVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@RestController
@RequestMapping("/api/v1/calibresult")
public class CalibresultController {
    @Autowired
    private ICalibresultService service;

    @Autowired
    private IMoCalibrationService moCalibrationService;

    public void setService(ICalibresultService service) {
        this.service = service;
    }

//    @GetMapping("statisticsLeftCenterOffsetOfXAndY")
//    @Operation(summary = "获取校准信息")
//    public Result statisticsLeftCenterOffsetOfXAndY(@RequestParam(required = false) String cameraSnPrefix,
//                       @RequestParam(required = false) Integer stationNumber) {
//        CalibresultLeftCenterOffsetOfXAndYDto calibresultVo = service.listCalibresultVo(cameraSnPrefix, stationNumber);
//
//        double xAverage = StatisticsCalculator.calculateAverage(calibresultVo.getLeftCenterOffsetXs());
//        double yAverage = StatisticsCalculator.calculateAverage(calibresultVo.getLeftCenterOffsetYs());
//        double xMedian = StatisticsCalculator.calculateMedian(calibresultVo.getLeftCenterOffsetXs());
//        double yMedian = StatisticsCalculator.calculateMedian(calibresultVo.getLeftCenterOffsetYs());
//        TreeMap<Integer, Integer> xcountOccurrences = StatisticsCalculator.countOccurrences(calibresultVo.getLeftCenterOffsetXs());
//        TreeMap<Integer, Integer> ycountOccurrences = StatisticsCalculator.countOccurrences(calibresultVo.getLeftCenterOffsetYs());
//        CalibresultstatisticsLeftCenterOffsetOfXAndYVo leftCenterOffsetOfXAndYVo =
//                new CalibresultstatisticsLeftCenterOffsetOfXAndYVo(xAverage, yAverage, xMedian, yMedian, xcountOccurrences, ycountOccurrences);
//        return Result.ok(leftCenterOffsetOfXAndYVo);
//    }

    @GetMapping("statisticsLeftCenterOffsetOfXAndY")
    @Operation(summary = "获取校准信息")
    public Result statisticsLeftCenterOffsetOfXAndY(@RequestParam(required = false) String cameraSnPrefix,
                                                    @RequestParam(required = false) Integer stationNumber,
                                                    @RequestParam(required = false) String startDate,
                                                    @RequestParam(required = false) String endDate) {
        CalibresultLeftCenterOffsetOfXAndYDto calibresultVo = service.listCalibresultVo(cameraSnPrefix, stationNumber, startDate, endDate);

        double xAverage = StatisticsCalculator.calculateAverage(calibresultVo.getLeftCenterOffsetXs());
        double yAverage = StatisticsCalculator.calculateAverage(calibresultVo.getLeftCenterOffsetYs());
        double xMedian = StatisticsCalculator.calculateMedian(calibresultVo.getLeftCenterOffsetXs());
        double yMedian = StatisticsCalculator.calculateMedian(calibresultVo.getLeftCenterOffsetYs());
        double xRange = Collections.max(calibresultVo.getLeftCenterOffsetXs()) - Collections.min(calibresultVo.getLeftCenterOffsetXs());
        double yRange = Collections.max(calibresultVo.getLeftCenterOffsetYs()) - Collections.min(calibresultVo.getLeftCenterOffsetYs());
        TreeMap<Integer, Integer> xcountOccurrences = StatisticsCalculator.countOccurrences(calibresultVo.getLeftCenterOffsetXs());
        TreeMap<Integer, Integer> ycountOccurrences = StatisticsCalculator.countOccurrences(calibresultVo.getLeftCenterOffsetYs());
        CalibresultstatisticsLeftCenterOffsetOfXAndYVo leftCenterOffsetOfXAndYVo =
                new CalibresultstatisticsLeftCenterOffsetOfXAndYVo(xAverage, yAverage, xMedian, yMedian, xcountOccurrences, ycountOccurrences, xRange, yRange);
        return Result.ok(leftCenterOffsetOfXAndYVo);
    }

}
