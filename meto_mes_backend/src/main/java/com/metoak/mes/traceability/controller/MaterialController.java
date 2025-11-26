package com.metoak.mes.traceability.controller;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.k3Cloud.service.IMaterialService;
import com.metoak.mes.traceability.vo.MaterialVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mes/v1/materials")
public class MaterialController {

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

    @DeleteMapping("/material-bindings/{id}")
    @Operation(summary = "根据ID删除单个物料绑定信息")
    public Result<Boolean> deleteBindingInfo(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID 不能为空且必须为正整数");
        }
        System.out.println(id);
        boolean isDeleted = materialService.deleteById(id);

        return Result.ok(isDeleted);
    }
}
