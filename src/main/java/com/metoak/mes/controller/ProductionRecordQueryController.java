package com.metoak.mes.controller;

import com.metoak.mes.common.config.DatabaseConfig;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.query.ProductionRecordQueryService;
import com.metoak.mes.dto.ProductionRecordDto;
import com.metoak.mes.dto.ProductionRecordQueryRequest;
import com.metoak.mes.entity.MoAutoAdjustSt07;
import com.metoak.mes.entity.MoAutoAdjustSt08;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

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
//            @RequestParam(defaultValue = "0") int positionOffset,
            @RequestParam(required = false) String[] attrNos,
            @RequestParam(required = false) int origin,
            @RequestParam(required = false) int device,
            @RequestParam(required = false) int station,
            @RequestParam(required = false) int position,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime
    ) {
//        Class<?> entityClass = resolveEntityClass(request.getEntityClassName());
        DatabaseConfig databaseConfig;
        if (origin == 1) {
            databaseConfig = DatabaseConfig.builder().url("jdbc:mysql://11.11.11.13:3306/mo_mes_db").username("root").password("momeshou").build();
        } else {
            databaseConfig = DatabaseConfig.builder().url("jdbc:mysql://192.168.188.11:3306/mo_mes_db").username("root").password("momeshou").build();
        }
        List<ProductionRecordDto> dtos = Collections.emptyList();
        int positionOffset = 0;
        if (device == 1 || device == 2) {
            positionOffset = 1;
        }
        if (device == 2 || device == 3) {
            dtos = productionRecordQueryService.queryMethod1(
                    databaseConfig,
                    MoAutoAdjustSt08.class,
                    positionOffset,
                    attrNos,
                    "add_time",
                    startTime,
                    endTime
            );
        } else if (device == 1) {
            dtos = productionRecordQueryService.queryMethod2(
                    databaseConfig,
                    positionOffset,
                    attrNos,
                    startTime,
                    endTime
            );
        }
        return Result.ok(dtos);
    }

    @GetMapping("spc")
    @Operation(summary = "SPC")
//    @RequestBody @Valid ProductionRecordQueryRequest request
    public Result<List<String>> spc(
            @RequestParam(required = false) String[] attrNos,
            @RequestParam(required = false) int origin,
            @RequestParam(required = false) int device,
            @RequestParam(required = false) int station,
            @RequestParam(required = false) int position,
            @RequestParam(required = false) int stage,
            @RequestParam(required = false) int count,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime
    ) {

        return Result.ok(dtos);
    }


    private Class<?> resolveEntityClass(String className) {
        if (!StringUtils.hasText(className)) {
            throw new IllegalArgumentException("实体类名称不能为空");
        }

        String resolved = className.contains(".") ? className : "com.metoak.mes.entity." + className;
        try {
            return Class.forName(resolved);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("未找到实体类: " + className, e);
        }
    }
}
