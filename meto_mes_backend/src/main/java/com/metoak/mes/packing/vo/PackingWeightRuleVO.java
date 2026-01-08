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

    private Integer specQuantity;

    private BigDecimal unitWeight;

    private BigDecimal tareWeight;

    private BigDecimal weightTolerance;

    private LocalDateTime createdAt;
}
