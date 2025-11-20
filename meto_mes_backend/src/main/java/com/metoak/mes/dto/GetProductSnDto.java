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
public class GetProductSnDto {

    @NotBlank(message = "工单编号不能为空")
    @Schema(description = "工单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String workOrderNo;

}