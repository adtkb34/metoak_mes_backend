package com.metoak.mes.params.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "ParamDetailCreateDto", description = "参数集创建请求对象")
public class ParamDetailCreateDto {

    @ApiModelProperty(value = "Base ID")
    private Long baseId;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "参数集")
    private String params;

    private String createdBy;

}