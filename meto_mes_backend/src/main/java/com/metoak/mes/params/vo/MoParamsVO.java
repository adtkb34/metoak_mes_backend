package com.metoak.mes.params.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MoParamsVo", description = "参数集展示对象")
public class MoParamsVo {

    @ApiModelProperty("参数BaseId")
    private Long baseId;

    @ApiModelProperty("参数详情Id")
    private Long detailId;

    @ApiModelProperty("参数集类型")
    private Integer type;

    @ApiModelProperty("关联项（工艺 / 工单 / 工序等）")
    private String relation;

    @ApiModelProperty("参数集名称")
    private String name;

    @ApiModelProperty("工艺编号")
    private String flowNo;

    @ApiModelProperty("工单ID")
    private Long orderId;

    @ApiModelProperty("工序编号，参数集归属的工序（可为空）")
    private String stepTypeNo;

    @ApiModelProperty("参数集描述")
    private String description;

    @ApiModelProperty("参数集版本")
    private String version;

    @ApiModelProperty("创建人")
    private String createdBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
}
