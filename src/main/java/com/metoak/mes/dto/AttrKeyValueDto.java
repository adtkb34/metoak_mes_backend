package com.metoak.mes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "参数信息")
public class AttrKeyValueDto {

    @Schema(description = "参数编号")
    private String no;

    @Schema(description = "参数")
    private String key;

    @Schema(description = "参数值")
    private String val;

    @Schema(description = "位置")
    private Integer position;

    @Override
    public String toString() {
        return "AttrKeyValueDto{" +
                "no='" + no + '\'' +
//                ", key='" + key + '\'' +
                ", val='" + val + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
