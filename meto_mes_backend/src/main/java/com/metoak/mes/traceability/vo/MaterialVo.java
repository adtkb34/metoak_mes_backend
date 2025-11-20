package com.metoak.mes.traceability.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialVo {

    @JsonProperty("material_code")
    private String materialCode;

    @JsonProperty("material_name")
    private String materialName;
}
