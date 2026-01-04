package com.metoak.mes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.ResultBean;
import com.metoak.mes.common.util.SortUtil;
import com.metoak.mes.dto.LoginDto;
import com.metoak.mes.dto.LoginResponseDto;
import com.metoak.mes.entity.MoAutoAdjustSt08;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.service.IMoAutoAdjustSt08Service;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * AA调焦数据表 前端控制器
 * </p>
 *
 * @author kevin
 * @since 2025-06-25 20:41:32
 */
@RestController
@RequestMapping("/api/v1/moAutoAdjustSt08")
public class MoAutoAdjustSt08Controller {

    @Autowired
    private IMoAutoAdjustSt08Service moAutoAdjustSt08Service;

    @GetMapping
    public ResultBean<List<MoAutoAdjustSt08>> fetch(@RequestParam(required = false) String productSn,
                                                    @RequestParam(required = false) String startTime,
                                                    @RequestParam(required = false) String endTime) {
        LocalDateTime start = null;
        LocalDateTime end = null;
        if (startTime != null && !startTime.isBlank()) {
            start = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        if (endTime != null && !endTime.isBlank()) {
            end = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        LambdaQueryWrapper<MoAutoAdjustSt08> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(productSn != null, MoAutoAdjustSt08::getBeamSn, productSn);
        wrapper.gt(startTime != null, MoAutoAdjustSt08::getAddTime, start);
        wrapper.le(endTime != null, MoAutoAdjustSt08::getAddTime, end);
        List<MoAutoAdjustSt08> list = moAutoAdjustSt08Service.list(wrapper);
        SortUtil.sortByFields(list, "moProcessStepProductionResultId", "beamSn", "position", "stage");
        return ResultBean.ok(list);
    }

}
