package com.metoak.mes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class DeviceStatusDto {

    @NotBlank(message = "SN不能为空")
    @Schema(description = "SN", requiredMode = Schema.RequiredMode.REQUIRED)
    private String productMaterialNo;

    @NotBlank(message = "设备编号号不能为空")
    @Schema(description = "设备编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String deviceNo;

    @NotEmpty(message = "状态参数不能为空")
    @Schema(description = "状态参数", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<AttrKeyValDto> attrKeyVals;

}