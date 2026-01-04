package com.metoak.mes.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.dto.*;
import com.metoak.mes.service.IProcessStepProductionRecords2Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/mes/v2/production")
@Tag(name = "生产2")
public class ProductionV2Controller {

    @Autowired
    private IProcessStepProductionRecords2Service processStepProductionRecordsService;

    @PostMapping("result/upload")
    @Operation(summary = "添加生产记录")
    public Result<Long> add(@RequestBody ProductionRecordDto productionRecordDto) {
        return Result.ok(processStepProductionRecordsService.add(productionRecordDto));
    }

    @PostMapping("executable/check")
    @Operation(summary = "是否具备执行条件")
    public Result<Boolean> executable(@RequestBody ExecutableDto executableDto) {
        return Result.ok(processStepProductionRecordsService.executable(executableDto));
    }

}
