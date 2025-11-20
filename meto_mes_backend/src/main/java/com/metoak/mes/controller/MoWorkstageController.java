package com.metoak.mes.controller;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.dto.WorkOrderDto;
import com.metoak.mes.entity.MoWorkstage;
import com.metoak.mes.service.IMoWorkstageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 工序表 前端控制器
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@RestController
@RequestMapping("/api/mes/v1/basic-information/process-stages")
public class MoWorkstageController {

    @Autowired
    private IMoWorkstageService moWorkstageService;


    @GetMapping
    @Operation(summary = "获取所有的工序")
    public Result<List<MoWorkstage>> fetchMulti() {
        return Result.ok(moWorkstageService.list());
    }

    @PostMapping
    @Operation(summary = "添加工序")
    public Result<Integer> add(@RequestBody @Valid MoWorkstage moWorkstage) {
        moWorkstage.setId(null);
        AtomicInteger maxStepCode = new AtomicInteger(0);
        moWorkstageService.list().forEach(item -> {
            int code = Integer.parseInt(item.getStageCode().substring(5).trim());
            maxStepCode.set(Math.max(maxStepCode.get(), code));
        });
        int result = maxStepCode.get() + 1;
        moWorkstage.setStageCode("Step " + result);

        moWorkstageService.save(moWorkstage);

        return Result.ok(moWorkstage.getId());
    }

    @PatchMapping
    @Operation(summary = "更新工序")
    public Result<Boolean> edit(@RequestBody @Valid MoWorkstage moWorkstage) {

        return Result.ok(moWorkstageService.updateById(moWorkstage));
    }

}
