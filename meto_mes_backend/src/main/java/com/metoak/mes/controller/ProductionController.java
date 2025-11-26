package com.metoak.mes.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.dto.*;
import com.metoak.mes.service.IProcessStepProductionRecordsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.metoak.mes.enums.ResultCodeEnum.PRO_PRODUCT_SN_NOT_FOUND_BY_WORKORDER_NO;

@RestController
@RequestMapping("/api/mes/v1/production")
@Tag(name = "生产")
public class ProductionController {

    @Autowired
    private IProcessStepProductionRecordsService processStepProductionRecordsService;

    @PostMapping("workOrder/sn/fetch")
    @Operation(summary = "通过工单获取衡量SN")
    public Result<GetProductSnResponseDto> fetchProductSn(@RequestBody @Valid GetProductSnDto getProductSnDto) {
        GetProductSnResponseDto getProductSnResponseDto = processStepProductionRecordsService.fetchProductSn(getProductSnDto);
        if (getProductSnResponseDto == null) {
            return Result.fail(PRO_PRODUCT_SN_NOT_FOUND_BY_WORKORDER_NO.getCode(), "没找到此工单的序列号");
        } else {
            return Result.ok(getProductSnResponseDto);
        }
    }

    @PostMapping("executable/check")
    @Operation(summary = "是否具备执行条件")
    public Result<Boolean> executable(@RequestBody ExecutableDto executableDto) {
        return Result.ok(processStepProductionRecordsService.executable(executableDto));
    }

    @PostMapping("spec/fetch")
    @Operation(summary = "工程参数配置")
    public Result<AttrSpecDto> fetchAttrSpec(@RequestBody FetchAttrSpecDto fetchAttrSpecDto) {
        return Result.ok(processStepProductionRecordsService.fetchAttrSpec(fetchAttrSpecDto));
    }

    @PostMapping("result/upload")
    @Operation(summary = "添加生产记录")
    public Result<Long> add(@RequestBody ProductionRecordDto productionRecordDto) {
        return Result.ok(processStepProductionRecordsService.add(productionRecordDto));
    }

    @PostMapping("result/bind")
    @Operation(summary = "物料绑定记录")
    public Result<List<Long>> binding(@RequestBody ProductionRecordDto productionRecordDto) {
        System.out.println("--------------------binding--------------------");
        List<Long> bindings = processStepProductionRecordsService.binding(productionRecordDto);
        System.out.println(bindings);
        System.out.println("--------------------binding--------------------");

        return Result.ok(bindings);
    }

    @PostMapping("judgement/check")
    @Operation(summary = "综合判定确认")
    public Result<Boolean> judgement(@RequestBody JudgementDto judgementDto) {
        return Result.ok(processStepProductionRecordsService.judgement(judgementDto));
    }

    @PostMapping("device/status/upload")
    @Operation(summary = "设备状态监控")
    public Result<Boolean> device(@RequestBody DeviceStatusDto deviceStatusDto) throws JsonProcessingException {
        return Result.ok(processStepProductionRecordsService.device(deviceStatusDto));
    }

}
