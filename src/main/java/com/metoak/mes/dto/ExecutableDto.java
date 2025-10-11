package com.metoak.mes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecutableDto {

    @NotBlank(message = "SN不能为空")
    @Schema(description = "SN", requiredMode = Schema.RequiredMode.REQUIRED)
    private String productSn;

    @Schema(description = "批次号")
    private String productBatchNo;

    @NotBlank(message = "产品物料编号不能为空")
    @Schema(description = "产品物料编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String productMaterialNo;

    @Schema(description = "工序类型")
    private String stepType;

    @NotBlank(message = "工序类型编号不能为空")
    @Schema(description = "工序类型编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String stepTypeNo;

}