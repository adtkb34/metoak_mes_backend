package com.metoak.mes.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "ParamsBaseDto对象", description = "参数集基础信息DTO")
public class ParamsBaseDto {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("参数集名称，含目的信息")
    private String name;

    @ApiModelProperty("参数集类型：0-工程，1-工艺")
    private Integer type;

    @ApiModelProperty("产品物料编号")
    private String materialCode;

    @ApiModelProperty("工序编号，参数集归属的工序")
    private String stepTypeNo;

    @ApiModelProperty("创建人，用于审计")
    private String createdBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
}