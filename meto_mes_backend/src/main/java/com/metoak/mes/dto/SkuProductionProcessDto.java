package com.metoak.mes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "产品执行工序的生产记录")
public class SkuProductionProcessDto {

    @Schema(description = "产品SN")
    protected String productSn;

    @Schema(description = "车间编号")
    protected String workshopNo;

    @Schema(description = "工站编号")
    protected String workstationNo;

    @Schema(description = "plc")
    protected String plc;

    @Schema(description = "plc版本")
    protected String plcVersion;

    @Schema(description = "操作员")
    protected String operator;

//    @Schema(description = "结束执行")
//    protected LocalDateTime productionEndTime;
//
//    @Schema(description = "开始执行")
//    protected LocalDateTime productionStartTime;

    @Schema(description = "工序编号")
    protected String productionProcessNo;

//    @Schema(description = "工序错误信息")
//    protected String productionProcessError;

//    @Schema(description = "工序错误信息编号")
//    protected String productionProcessErrorNo;
//
//    @Schema(description = "工序参数信息")
//    protected List<AttrKeyValueDto> attrKeyValues;

    @Override
    public String toString() {
        return "SkuProductionProcessDto{" +
                "productSn=" + productSn +
                ", workstationNo=" + workstationNo +
                ", plc='" + plc + '\'' +
                ", plcVersion='" + plcVersion + '\'' +
                ", operator=" + operator +
//                ", productionEndTime=" + productionEndTime +
//                ", productionStartTime=" + productionStartTime +
                ", productionProcessNo=" + productionProcessNo +
//                ", productionProcessConfigId=" + productionProcessConfigId +
//                ", productionProcessError=" + productionProcessError +
//                ", productionProcessErrorNo=" + productionProcessErrorNo +
//                ", attrKeyValues=" + attrKeyValues +
                '}';
    }
}
