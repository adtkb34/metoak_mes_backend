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
public class SkuProductionProcessTaskDto {

    @Schema(description = "结束执行")
    protected LocalDateTime endTime;

    @Schema(description = "开始执行")
    protected LocalDateTime startTime;

    @Schema(description = "工序任务编号")
    protected String productionProcessTaskNo;

    @Schema(description = "工序错误信息")
    private String productionProcessError;

    @Schema(description = "工序错误信息编号")
    private String productionProcessErrorNo;

    @Schema(description = "工序参数信息")
    private List<AttrKeyValueDto> attrKeyValues;


    @Override
    public String toString() {
        return "SkuProductionProcessTaskDto{" +
                ", endTime=" + endTime +
                ", startTime=" + startTime +
                ", productionProcessTaskNo=" + productionProcessTaskNo +
                ", productionProcessError=" + productionProcessError +
                ", productionProcessErrorNo=" + productionProcessErrorNo +
                ", attrKeyValues=" + attrKeyValues +
                '}';
    }
}
