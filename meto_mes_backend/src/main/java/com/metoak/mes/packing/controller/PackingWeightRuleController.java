package com.metoak.mes.packing.controller;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.packing.dto.PackingWeightRuleCreateDto;
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
        return packingWeightRuleService.listRules();
    }

    @GetMapping("/by-product-code")
    @Operation(summary = "根据产品编码获取装箱重量规则")
    public Result<PackingWeightRuleVO> getRuleByProductCode(
            @RequestParam("productCode") @NotBlank(message = PRODUCT_CODE_REQUIRED_MESSAGE) String productCode) {
        return packingWeightRuleService.getRuleByProductCode(productCode);
    }

    @PostMapping
    @Operation(summary = "新建装箱重量规则")
    public Result<Long> createRule(@RequestBody @Valid PackingWeightRuleCreateDto createDto) {
        return packingWeightRuleService.createRule(createDto);
    }
}
