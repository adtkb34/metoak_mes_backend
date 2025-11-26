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
 * 参数集详情表
 * </p>
 *
 * @author Qoder
 * @since 2025-11-18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_params_detail")
@ApiModel(value = "MoParamsDetail对象", description = "参数集详情表")
public class MoParamsDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("关联 param_base.id，多对一")
    private Long baseId;

    @ApiModelProperty("版本说明，可记录变更内容")
    private String description;

    @ApiModelProperty("版本：主号")
    private Integer versionMajor;

    @ApiModelProperty("版本：副号")
    private Integer versionMinor;

    @ApiModelProperty("版本：修订号")
    private Integer versionPatch;

    @ApiModelProperty("参数内容（JSON），完整参数")
    private String params;

    @ApiModelProperty("是否是当前启用版本，工艺参数集对每个 name 仅允许一个 active")
    private Integer isActive;

    @ApiModelProperty("创建人，用于审计")
    private String createdBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
}