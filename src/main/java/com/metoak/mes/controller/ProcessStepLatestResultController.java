package com.metoak.mes.controller;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.service.ProcessStepLatestResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mes/v1/process-step")
@Tag(name = "工序结果查询")
public class ProcessStepLatestResultController {

    private final ProcessStepLatestResultService processStepLatestResultService;

    public ProcessStepLatestResultController(ProcessStepLatestResultService processStepLatestResultService) {
        this.processStepLatestResultService = processStepLatestResultService;
    }

    @GetMapping("/latest-success")
    @Operation(summary = "通过序列号查询工序最近一次是否成功")
    public Result<Boolean> getLatestSuccess(@RequestParam String productSn,
                                            @RequestParam String stepTypeNo) {
        Boolean isSuccess = processStepLatestResultService
                .findLatestSuccess(productSn, stepTypeNo)
                .orElse(Boolean.FALSE);
        return Result.ok(isSuccess);
    }
}
