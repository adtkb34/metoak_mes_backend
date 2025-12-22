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
@ApiModel(value = "MoParamsVO", description = "参数集展示对象")
public class MoParamsVO {

    @ApiModelProperty("参数集类型：0-工程，1-工艺")
    private Integer type;

    @ApiModelProperty("关联项（工艺 / 工单 / 工序等）")
    private String relation;

    @ApiModelProperty("参数集名称")
    private String name;

    @ApiModelProperty("参数集描述")
    private String description;

    @ApiModelProperty("参数集版本")
    private String version;

    @ApiModelProperty("创建人")
    private String createdBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
}
