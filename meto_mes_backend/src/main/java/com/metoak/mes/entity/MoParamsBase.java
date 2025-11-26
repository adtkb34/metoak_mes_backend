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
 * 参数集基础信息表
 * </p>
 *
 * @author kevin
 * @since 2025-11-18 19:08:39
 */
@TableName("mo_params_base")
@ApiModel(value = "MoParamsBase对象", description = "参数集基础信息表")
public class MoParamsBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("参数集名称，含目的信息")
    private String name;

    @ApiModelProperty("参数集类型：0-工程，1-工艺")
    private Byte type;

    @ApiModelProperty("产品物料编号")
    private String materialCode;

    @ApiModelProperty("工序编号，参数集归属的工序")
    private String stepTypeNo;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getStepTypeNo() {
        return stepTypeNo;
    }

    public void setStepTypeNo(String stepTypeNo) {
        this.stepTypeNo = stepTypeNo;
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
        return "MoParamsBase{" +
            "id = " + id +
            ", name = " + name +
            ", type = " + type +
            ", materialCode = " + materialCode +
            ", stepTypeNo = " + stepTypeNo +
            ", createdBy = " + createdBy +
            ", createdAt = " + createdAt +
        "}";
    }
}
