package com.metoak.mes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "产品执行工序的生产记录")
public class SkuProductionProcessDto2 {

    @Schema(description = "产品SN")
    protected String productSn;

    @Schema(description = "工站编号")
    protected String workstationNo;

    @Schema(description = "软件工具")
    protected String softwareTool;

    @Schema(description = "软件工具版本")
    protected String softwareToolVersion;

    @Schema(description = "操作员")
    protected String operatorUserName;

    @Schema(description = "结束执行")
    protected LocalDateTime endTime;

    @Schema(description = "开始执行")
    protected LocalDateTime startTime;

    @Schema(description = "工序编号")
    protected String productionProcessNo;

    @Schema(description = "工序错误信息")
    private String productionProcessError;

    @Schema(description = "工序错误信息编号")
    private String productionProcessErrorNo;

    @Schema(description = "工序参数信息")
    private List<AttrKeyValueDto> attrKeyValues;

    @Schema(description = "工序参数信息")
    private List<SkuProductionProcessTaskDto> taskResults;


    @Override
    public String toString() {
        return "SkuProductionProcessDto{" +
                "productSn=" + productSn +
                ", workstationNo=" + workstationNo +
                ", softwareTool='" + softwareTool + '\'' +
                ", softwareToolVersion='" + softwareToolVersion + '\'' +
                ", operatorUserName=" + operatorUserName +
                ", endTime=" + endTime +
                ", startTime=" + startTime +
                ", productionProcessNo=" + productionProcessNo +
                ", productionProcessError=" + productionProcessError +
                ", productionProcessErrorNo=" + productionProcessErrorNo +
                ", taskResults=" + taskResults +
                '}';
    }
}
