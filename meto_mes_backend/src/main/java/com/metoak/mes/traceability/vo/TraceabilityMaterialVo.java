package com.metoak.mes.traceability.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraceabilityMaterialVo {

    @JsonProperty("material_code")
    private String materialCode;

    @JsonProperty("material_name")
    private String materialName;
}
