package com.metoak.mes.packing.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BarcodePackageSaveDto {

    private static final String PACKING_CODE_REQUIRED_MESSAGE = "包装码不能为空";
    private static final String PRODUCT_CODE_REQUIRED_MESSAGE = "产品编码不能为空";

    @NotBlank(message = PACKING_CODE_REQUIRED_MESSAGE)
    private String packingCode;

    @NotBlank(message = PRODUCT_CODE_REQUIRED_MESSAGE)
    private String productCode;
}
