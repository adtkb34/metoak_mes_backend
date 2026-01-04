package com.metoak.mes.params.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ParamsQueryDto", description = "参数集查询请求对象")
public class ParamsQueryDto {
    
    @ApiModelProperty(value = "参数集类型")
    private Integer type;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "工艺编号")
    private String flowNo;

    @ApiModelProperty(value = "工单ID")
    private Long orderId;

    @ApiModelProperty(value = "工序编号")
    private String stepTypeNo;

}