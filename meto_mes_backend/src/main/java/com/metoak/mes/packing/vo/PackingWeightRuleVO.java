package com.metoak.mes.packing.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PackingWeightRuleVO {

    private Long id;

    private String productCode;

    private Integer fullBoxQuantity;

    private BigDecimal singleProductWeight;

    private BigDecimal fullBoxPackageWeight;

    private BigDecimal allowedDeviation;

    private String unit;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
