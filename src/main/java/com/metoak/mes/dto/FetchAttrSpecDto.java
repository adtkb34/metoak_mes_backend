package com.metoak.mes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FetchAttrSpecDto {

    @NotBlank(message = "SN不能为空")
    @Schema(description = "SN", requiredMode = Schema.RequiredMode.REQUIRED)
    private String productSn;

    @NotBlank(message = "工站类型编号不能为空")
    @Schema(description = "工站类型编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String workstationTypeNo;

}