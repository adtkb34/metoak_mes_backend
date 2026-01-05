package com.metoak.mes.packing.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PackingWeightRuleCreateDto {

    private static final String PRODUCT_CODE_REQUIRED_MESSAGE = "产品编码不能为空";
    private static final String SPEC_QUANTITY_REQUIRED_MESSAGE = "整箱数量不能为空";
    private static final String SPEC_QUANTITY_MIN_MESSAGE = "整箱数量必须大于0";
    private static final String UNIT_WEIGHT_REQUIRED_MESSAGE = "单个产品重量不能为空";
    private static final String UNIT_WEIGHT_MIN_MESSAGE = "单个产品重量必须大于0";
    private static final String TARE_WEIGHT_REQUIRED_MESSAGE = "整箱包裹重量不能为空";
    private static final String TARE_WEIGHT_MIN_MESSAGE = "整箱包裹重量必须大于0";
    private static final String WEIGHT_TOLERANCE_REQUIRED_MESSAGE = "允许的误差不能为空";
    private static final String WEIGHT_TOLERANCE_MIN_MESSAGE = "允许的误差必须大于0";

    @NotBlank(message = PRODUCT_CODE_REQUIRED_MESSAGE)
    private String productCode;

    @NotNull(message = SPEC_QUANTITY_REQUIRED_MESSAGE)
    @Min(value = 1, message = SPEC_QUANTITY_MIN_MESSAGE)
    private Integer specQuantity;

    @NotNull(message = UNIT_WEIGHT_REQUIRED_MESSAGE)
    @DecimalMin(value = "0.000", inclusive = false, message = UNIT_WEIGHT_MIN_MESSAGE)
    private BigDecimal unitWeight;

    @NotNull(message = TARE_WEIGHT_REQUIRED_MESSAGE)
    @DecimalMin(value = "0.000", inclusive = false, message = TARE_WEIGHT_MIN_MESSAGE)
    private BigDecimal tareWeight;

    @NotNull(message = WEIGHT_TOLERANCE_REQUIRED_MESSAGE)
    @DecimalMin(value = "0.000", inclusive = false, message = WEIGHT_TOLERANCE_MIN_MESSAGE)
    private BigDecimal weightTolerance;
}
