package com.metoak.mes.packing.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PackingWeightRuleUpdateDto {

    private static final String ID_REQUIRED_MESSAGE = "ID不能为空";
    private static final String PRODUCT_CODE_REQUIRED_MESSAGE = "产品编码不能为空";
    private static final String FULL_BOX_QUANTITY_REQUIRED_MESSAGE = "整箱数量不能为空";
    private static final String FULL_BOX_QUANTITY_MIN_MESSAGE = "整箱数量必须大于0";
    private static final String SINGLE_PRODUCT_WEIGHT_REQUIRED_MESSAGE = "单个产品重量不能为空";
    private static final String SINGLE_PRODUCT_WEIGHT_MIN_MESSAGE = "单个产品重量必须大于0";
    private static final String FULL_BOX_PACKAGE_WEIGHT_REQUIRED_MESSAGE = "整箱包裹重量不能为空";
    private static final String FULL_BOX_PACKAGE_WEIGHT_MIN_MESSAGE = "整箱包裹重量必须大于0";
    private static final String ALLOWED_DEVIATION_REQUIRED_MESSAGE = "允许的误差不能为空";
    private static final String ALLOWED_DEVIATION_MIN_MESSAGE = "允许的误差必须大于0";
    private static final String UNIT_REQUIRED_MESSAGE = "单位不能为空";

    @NotNull(message = ID_REQUIRED_MESSAGE)
    private Long id;

    @NotBlank(message = PRODUCT_CODE_REQUIRED_MESSAGE)
    private String productCode;

    @NotNull(message = FULL_BOX_QUANTITY_REQUIRED_MESSAGE)
    @Min(value = 1, message = FULL_BOX_QUANTITY_MIN_MESSAGE)
    private Integer fullBoxQuantity;

    @NotNull(message = SINGLE_PRODUCT_WEIGHT_REQUIRED_MESSAGE)
    @DecimalMin(value = "0.000", inclusive = false, message = SINGLE_PRODUCT_WEIGHT_MIN_MESSAGE)
    private BigDecimal singleProductWeight;

    @NotNull(message = FULL_BOX_PACKAGE_WEIGHT_REQUIRED_MESSAGE)
    @DecimalMin(value = "0.000", inclusive = false, message = FULL_BOX_PACKAGE_WEIGHT_MIN_MESSAGE)
    private BigDecimal fullBoxPackageWeight;

    @NotNull(message = ALLOWED_DEVIATION_REQUIRED_MESSAGE)
    @DecimalMin(value = "0.000", inclusive = false, message = ALLOWED_DEVIATION_MIN_MESSAGE)
    private BigDecimal allowedDeviation;

    @NotBlank(message = UNIT_REQUIRED_MESSAGE)
    private String unit;
}
