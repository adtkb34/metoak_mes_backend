package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 参数集详情表
 * </p>
 *
 * @author kevin
 * @since 2025-11-18 19:08:39
 */
@TableName("mo_params_detail")
@ApiModel(value = "MoParamsDetail对象", description = "参数集详情表")
public class MoParamsDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键，同时用作token")
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
    private Boolean isActive;

    @ApiModelProperty("创建人，用于审计")
    private String createdBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersionMajor() {
        return versionMajor;
    }

    public void setVersionMajor(Integer versionMajor) {
        this.versionMajor = versionMajor;
    }

    public Integer getVersionMinor() {
        return versionMinor;
    }

    public void setVersionMinor(Integer versionMinor) {
        this.versionMinor = versionMinor;
    }

    public Integer getVersionPatch() {
        return versionPatch;
    }

    public void setVersionPatch(Integer versionPatch) {
        this.versionPatch = versionPatch;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "MoParamsDetail{" +
            "id = " + id +
            ", baseId = " + baseId +
            ", description = " + description +
            ", versionMajor = " + versionMajor +
            ", versionMinor = " + versionMinor +
            ", versionPatch = " + versionPatch +
            ", params = " + params +
            ", isActive = " + isActive +
            ", createdBy = " + createdBy +
            ", createdAt = " + createdAt +
        "}";
    }
}
