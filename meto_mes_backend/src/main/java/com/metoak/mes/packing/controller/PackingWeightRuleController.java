package com.metoak.mes.packing.controller;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.packing.dto.PackingWeightRuleCreateDto;
import com.metoak.mes.packing.dto.PackingWeightRuleUpdateDto;
import com.metoak.mes.packing.service.PackingWeightRuleService;
import com.metoak.mes.packing.vo.PackingWeightRuleVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mes/v1/packing/weight-rules")
@Validated
@Tag(name = "装箱重量规则")
public class PackingWeightRuleController {

    private static final String PRODUCT_CODE_REQUIRED_MESSAGE = "产品编码不能为空";

    @Autowired
    private PackingWeightRuleService packingWeightRuleService;

    @GetMapping
    @Operation(summary = "装箱重量规则列表")
    public Result<List<PackingWeightRuleVO>> listRules() {
        return Result.ok(packingWeightRuleService.listRules());
    }

    @GetMapping("/by-product-code")
    @Operation(summary = "根据产品编码获取装箱重量规则")
    public Result<PackingWeightRuleVO> getRuleByProductCode(
            @RequestParam("productCode") @NotBlank(message = PRODUCT_CODE_REQUIRED_MESSAGE) String productCode) {
        PackingWeightRuleVO rule = packingWeightRuleService.getRuleByProductCode(productCode);
        if (rule == null) {
            return Result.fail(ResultCodeEnum.RECORD_NOT_FOUND);
        }
        return Result.ok(rule);
    }

    @PostMapping
    @Operation(summary = "新建装箱重量规则")
    public Result<Long> createRule(@RequestBody @Valid PackingWeightRuleCreateDto createDto) {
        return Result.ok(packingWeightRuleService.createRule(createDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新装箱重量规则")
    public Result<Boolean> updateRule(@PathVariable("id") Long id,
                                      @RequestBody @Valid PackingWeightRuleUpdateDto updateDto) {
        updateDto.setId(id);
        boolean updated = packingWeightRuleService.updateRule(updateDto);
        if (!updated) {
            return Result.fail(ResultCodeEnum.RECORD_NOT_FOUND);
        }
        return Result.ok(true);
    }
}
