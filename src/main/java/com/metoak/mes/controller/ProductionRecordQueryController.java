package com.metoak.mes.controller;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.query.ProductionRecordQueryService;
import com.metoak.mes.dto.AttrKeyValDto;
import com.metoak.mes.dto.ProductionRecordDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            @RequestParam(required = false) String endTime
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
                count
        );
        return Result.ok(dtos);
    }

    @GetMapping("spc")
    @Operation(summary = "SPC")
//    @RequestBody @Valid ProductionRecordQueryRequest request
    public Result<List<String>> spc(
            @RequestParam(required = false) String[] attrKeys,
            @RequestParam(required = false) Integer origin,
            @RequestParam(required = false) Integer device,
            @RequestParam(required = false) Integer station,
            @RequestParam(required = false) Integer position,
            @RequestParam(required = false) Integer stage,
            @RequestParam Integer count,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime
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
                count
        );

        List<String> values = dtos.stream()
                .filter(Objects::nonNull)
                .flatMap(dto -> {
                    List<AttrKeyValDto> attrKeyVals = dto.getAttrKeyVals();
                    if (attrKeyVals == null) {
                        return Stream.empty();
                    }
                    return attrKeyVals.stream();
                })
                .map(AttrKeyValDto::getVal)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return Result.ok(values);
    }
}
