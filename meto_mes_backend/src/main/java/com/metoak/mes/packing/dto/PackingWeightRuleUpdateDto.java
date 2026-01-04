package com.metoak.mes.packing.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PackingWeightRuleUpdateDto {

    @NotNull(message = "ID不能为空")
    private Long id;

    @NotBlank(message = "产品型号不能为空")
    private String productModel;

    @NotNull(message = "整箱数量不能为空")
    @Min(value = 1, message = "整箱数量必须大于0")
    private Integer fullBoxQuantity;

    @NotNull(message = "单个产品重量不能为空")
    @DecimalMin(value = "0.000", inclusive = false, message = "单个产品重量必须大于0")
    private BigDecimal singleProductWeight;

    @NotNull(message = "整箱包裹重量不能为空")
    @DecimalMin(value = "0.000", inclusive = false, message = "整箱包裹重量必须大于0")
    private BigDecimal fullBoxPackageWeight;

    @NotNull(message = "允许的误差不能为空")
    @DecimalMin(value = "0.000", inclusive = false, message = "允许的误差必须大于0")
    private BigDecimal allowedDeviation;

    @NotBlank(message = "单位不能为空")
    private String unit;
}
