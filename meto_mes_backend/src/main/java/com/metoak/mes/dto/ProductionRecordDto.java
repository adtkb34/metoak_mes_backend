package com.metoak.mes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductionRecordDto {

    @Schema(description = "产品SN")
    private String productSn;

    @Schema(description = "产品批次号")
    private String productBatchNo;

    @Schema(description = "产品物料码")
    private String productCode;

    @Schema(description = "框号")
    private String boxNumber;

    @Schema(description = "作业编号")
    private String jobId;

    @NotBlank(message = "工序类型不能为空")
    @Schema(description = "工序类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private String stepType;

    @NotBlank(message = "工序类型编号不能为空")
    @Schema(description = "工序类型编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String stepTypeNo;

    @NotBlank(message = "任务名称不能为空")
    @Schema(description = "任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String task;

    @NotBlank(message = "任务编号不能为空")
    @Schema(description = "任务编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String taskNo;

    @NotBlank(message = "设备编号不能为空")
    @Schema(description = "设备编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String deviceNo;

    @NotBlank(message = "操作员不能为空")
    @Schema(description = "操作员", requiredMode = Schema.RequiredMode.REQUIRED)
    private String operator;

    @Schema(description = "操作员")
    private String operator_;

    @NotBlank(message = "软件工具不能为空")
    @Schema(description = "软件工具", requiredMode = Schema.RequiredMode.REQUIRED)
    private String softwareTool;

    @NotBlank(message = "软件工具版本不能为空")
    @Schema(description = "软件工具版本", requiredMode = Schema.RequiredMode.REQUIRED)
    private String softwareToolVersion;

    @NotBlank(message = "错误描述不能为空")
    @Schema(description = "错误描述", requiredMode = Schema.RequiredMode.REQUIRED)
    private String error;

    @NotBlank(message = "错误编号不能为空")
    @Schema(description = "错误编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String errorNo;

    @NotBlank(message = "规格值编号不能为空")
    @Schema(description = "规格值编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String attrSpecHeaderNo;

    @NotBlank(message = "开始时间不能为空")
    @Schema(description = "开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private String startTime;

    @NotBlank(message = "结束时间不能为空")
    @Schema(description = "结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private String endTime;

    @Schema(description = "上传时间")
    private String postTime;

    @Schema(description = "属性键值对")
    private List<AttrKeyValDto> attrKeyVals;

    @Schema(description = "产品物料编号")
    private List<MaterialDto> materials;

    @Schema(description = "产量")
    private OutputDto output;

    private Long engineeringParamsId;

    private Long flowParamsId;

    private Integer count = 1;

}