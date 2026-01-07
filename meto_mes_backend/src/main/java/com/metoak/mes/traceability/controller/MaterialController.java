package com.metoak.mes.traceability.controller;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.k3Cloud.service.IMaterialService;
import com.metoak.mes.traceability.vo.MaterialBindVo;
import com.metoak.mes.traceability.vo.MaterialVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/mes/v1/materials")
public class MaterialController {

    private static final String DEFAULT_DELETE_COUNT = "0";

    @Autowired
    private IMaterialService materialService;

    @GetMapping("/fromWorkOrder")
    @Operation(summary = "从工单获取获取物料列表")
    public Result<List<MaterialVo>> listMaterialsFromWorkOrder() throws Exception {
        return Result.ok(materialService.listMaterialsFromWorkOrder());
    }

    @GetMapping("/{materialCode}/bom")
    @Operation(summary = "通过物料料号获取BOM")
    public Result<List<MaterialVo>> listBomByMaterialCode(@PathVariable String materialCode) throws Exception {
        return Result.ok(materialService.getBomByMaterialCode(materialCode));
    }

    @GetMapping("/{materialCode}/batches")
    @Operation(summary = "通过物料编号获取物料批次号")
    public Result<List<String>> listBatchesByMaterialCode(@PathVariable String materialCode) throws Exception {
        return Result.ok(materialService.getBatchesByMaterialCode(materialCode));
    }

    @DeleteMapping("/deleteBinding")
    @Operation(summary = "根据产品序列号和物料ID删除物料绑定信息")
    public Result<Boolean> deleteBindingInfo(
            @RequestParam String productSn,
            @RequestParam String materialID,
            @RequestParam(defaultValue = DEFAULT_DELETE_COUNT) Integer count
    ) throws Exception {
        return Result.ok(materialService.deleteBindingInfo(productSn, materialID, count));
    }

    @Operation(summary = "获取所有绑定记录")
    @GetMapping("/materialBindings")
    public Result<List<MaterialBindVo>> getBindings(
            @RequestParam(required = false) String materialCode,
            @RequestParam(required = false) String cameraSn,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime
    ) throws Exception {
        return Result.ok(materialService.getBindings(materialCode, cameraSn, startTime, endTime));
    }
}
