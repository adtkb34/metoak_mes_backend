package com.metoak.mes.controller;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.query.ProductionRecordQueryService;
import com.metoak.mes.dto.ProductionRecordDto;
import com.metoak.mes.dto.ProductionRecordQueryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mes/v1/production-record-query")
@Tag(name = "生产记录查询")
public class ProductionRecordQueryController {

    private final ProductionRecordQueryService productionRecordQueryService;

    public ProductionRecordQueryController(ProductionRecordQueryService productionRecordQueryService) {
        this.productionRecordQueryService = productionRecordQueryService;
    }

    @PostMapping("/method1")
    @Operation(summary = "生产记录查询方法1")
    public Result<List<ProductionRecordDto>> queryMethod1(@RequestBody @Valid ProductionRecordQueryRequest request) {
        Class<?> entityClass = resolveEntityClass(request.getEntityClassName());
        List<ProductionRecordDto> dtos = productionRecordQueryService.queryMethod1(
                request.getDatabaseConfig(),
                entityClass
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
