package com.metoak.mes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogoutDto {

    @NotBlank(message = "TOKEN不能为空")
    @Schema(description = "TOKEN", requiredMode = Schema.RequiredMode.REQUIRED)
    private String token;

}