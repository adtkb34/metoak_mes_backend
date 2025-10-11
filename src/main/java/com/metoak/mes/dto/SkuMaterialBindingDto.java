package com.metoak.mes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "物料批次信息")
public class SkuMaterialBindingDto extends SkuProductionProcessDto {

    @Schema(description = "产品新SN")
    private String newProductSn;

    @Schema(description = "物料")
    private List<MaterialDto> materials;

    @Override
    public String toString() {
        return "SkuMaterialBindingDto{" +
                "newProductSn='" + newProductSn + '\'' +
                ", productSn='" + productSn + '\'' +
                ", materials=" + materials +
                ", productSn='" + productSn + '\'' +
                ", workshopNo='" + workshopNo + '\'' +
                ", workstationNo='" + workstationNo + '\'' +
                ", plc='" + plc + '\'' +
                ", plcVersion='" + plcVersion + '\'' +
                ", operator='" + operator + '\'' +
//                ", productionEndTime=" + productionEndTime +
//                ", productionStartTime=" + productionStartTime +
                ", productionProcessNo='" + productionProcessNo + '\'' +
//                ", productionProcessConfigId=" + productionProcessConfigId +
//                ", productionProcessError='" + productionProcessError + '\'' +
//                ", productionProcessErrorNo='" + productionProcessErrorNo + '\'' +
//                ", attrKeyValues=" + attrKeyValues +
                '}';
    }
}