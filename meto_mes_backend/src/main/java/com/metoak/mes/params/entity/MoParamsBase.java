package com.metoak.mes.params.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 参数集基础信息表
 * </p>
 *
 * @author Qoder
 * @since 2025-11-18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_params_base")
@ApiModel(value = "MoParamsBase对象", description = "参数集基础信息表")
public class MoParamsBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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