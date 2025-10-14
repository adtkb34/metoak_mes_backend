package com.metoak.mes.controller;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.query.ProductionRecordQueryService;
import com.metoak.mes.dto.AttrKeyValDto;
import com.metoak.mes.dto.ProductionRecordDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mes/v1/production-record-query")
@Tag(name = "生产记录查询")
public class ProductionRecordQueryController {

    private final ProductionRecordQueryService productionRecordQueryService;

    public ProductionRecordQueryController(ProductionRecordQueryService productionRecordQueryService) {
        this.productionRecordQueryService = productionRecordQueryService;
    }

    @GetMapping
    @Operation(summary = "生产记录查询方法1")
//    @RequestBody @Valid ProductionRecordQueryRequest request
    public Result<List<ProductionRecordDto>> queryMethod1(
            @RequestParam(required = false) String[] attrKeys,
            @RequestParam(required = false) Integer origin,
            @RequestParam(required = false) Integer device,
            @RequestParam(required = false) Integer station,
            @RequestParam(required = false) Integer position,
            @RequestParam(required = false) Integer stage,
            @RequestParam Integer count,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam String stepTypeNo
    ) {
        List<ProductionRecordDto> dtos = productionRecordQueryService.queryMethod(
                attrKeys,
                origin,
                device,
                station,
                position,
                stage,
                startTime,
                endTime,
                count,
                stepTypeNo
        );
        return Result.ok(dtos);
    }

    @GetMapping("spc")
    @Operation(summary = "SPC")
//    @RequestBody @Valid ProductionRecordQueryRequest request
    public Result<Map<String, List<String>>> spc(
            @RequestParam(required = false) String[] attrKeys,
            @RequestParam(required = false) Integer origin,
            @RequestParam(required = false) Integer device,
            @RequestParam(required = false) Integer station,
            @RequestParam(required = false) Integer position,
            @RequestParam(required = false) Integer stage,
            @RequestParam Integer count,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam String stepTypeNo
    ) {
        List<ProductionRecordDto> dtos = productionRecordQueryService.queryMethod(
                attrKeys,
                origin,
                device,
                station,
                position,
                stage,
                startTime,
                endTime,
                count,
                stepTypeNo
        );
        Map<String, List<String>> categorized = new LinkedHashMap<>();
        categorized.put("OK", new ArrayList<>());
        categorized.put("NG", new ArrayList<>());

        for (ProductionRecordDto dto : dtos) {
            if (dto == null) {
                continue;
            }
            String resultFlag = determineResultFlag(dto.getErrorNo());
            List<AttrKeyValDto> attrKeyVals = dto.getAttrKeyVals();
            if (attrKeyVals == null) {
                continue;
            }
            for (AttrKeyValDto attr : attrKeyVals) {
                if (attr == null) {
                    continue;
                }
                String value = attr.getVal();
                if (value == null) {
                    continue;
                }
                categorized.get(resultFlag).add(value);
            }
        }

        return Result.ok(categorized);
    }

    private String determineResultFlag(String errorNo) {
        if (errorNo == null) {
            return "NG";
        }
        String trimmed = errorNo.trim();
        if (trimmed.isEmpty()) {
            return "NG";
        }
        return "0".equals(trimmed) ? "OK" : "NG";
    }
}
