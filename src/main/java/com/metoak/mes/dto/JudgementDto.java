package com.metoak.mes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JudgementDto {

    @NotBlank(message = "产品序列号不能为空")
    @Schema(description = "产品序列号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String productSn;

}
