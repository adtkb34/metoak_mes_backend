package com.metoak.mes.traceability.controller;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.traceability.service.ITraceabilityService;
import com.metoak.mes.traceability.vo.TraceabilityMaterialVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/traceability")
public class TraceabilityController {

    private final ITraceabilityService traceabilityService;

    public TraceabilityController(ITraceabilityService traceabilityService) {
        this.traceabilityService = traceabilityService;
    }

    @GetMapping("/products")
    @Operation(summary = "获取产品列表")
    public Result<List<TraceabilityMaterialVo>> listProducts() {
        return Result.ok(traceabilityService.listProducts());
    }

    @GetMapping("/products/{materialCode}/bom")
    @Operation(summary = "通过产品料号获取BOM")
    public Result<List<TraceabilityMaterialVo>> listBomByMaterialCode(@PathVariable String materialCode) {
        return Result.ok(traceabilityService.getBomByMaterialCode(materialCode));
    }

    @GetMapping("/materials/{materialCode}/batches")
    @Operation(summary = "通过物料编号获取物料批次号")
    public Result<List<String>> listBatchesByMaterialCode(@PathVariable String materialCode) {
        return Result.ok(traceabilityService.getBatchesByMaterialCode(materialCode));
    }
}
