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

import static com.metoak.mes.common.result.ResultCodeEnum.*;


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



    @GetMapping
    public Result getLatest(@RequestParam String productSn) {
        return moImuCalibResultsService.getLatest(productSn);
    }

    @GetMapping("latest/filePath")
    public Result getLatest2(@RequestParam String productSn) {
        return moImuCalibResultsService.getLatest(productSn);
    }


}
