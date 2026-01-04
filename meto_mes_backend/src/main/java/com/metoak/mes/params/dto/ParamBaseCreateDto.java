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
@ApiModel(value = "ParamBaseCreateDto", description = "参数集创建请求对象")
public class ParamBaseCreateDto {

    @ApiModelProperty(value = "参数集名称")
    private String name;

    @ApiModelProperty(value = "参数集类型")
    private Integer type;

    @ApiModelProperty(value = "工艺编号")
    private String flowNo;

    @ApiModelProperty(value = "工单ID")
    private Long orderId;

    @ApiModelProperty(value = "工序编号")
    private String stepTypeNo;

    private String createdBy;

}