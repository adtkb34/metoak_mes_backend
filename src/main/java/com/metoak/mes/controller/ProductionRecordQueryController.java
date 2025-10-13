package com.metoak.mes.controller;

import com.metoak.mes.common.config.DatabaseConfig;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.query.ProductionRecordQueryService;
import com.metoak.mes.dto.ProductionRecordDto;
import com.metoak.mes.dto.ProductionRecordQueryRequest;
import com.metoak.mes.entity.MoAutoAdjustSt08;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    public Result<List<ProductionRecordDto>> queryMethod1() {
//        Class<?> entityClass = resolveEntityClass(request.getEntityClassName());
        DatabaseConfig readonlyuser = DatabaseConfig.builder().url("jdbc:mysql://172.24.81.104:3306/mo_mes_db").username("root").password("momeshou").build();
        List<ProductionRecordDto> dtos = productionRecordQueryService.queryMethod1(
                readonlyuser,
                MoAutoAdjustSt08.class
        );
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
