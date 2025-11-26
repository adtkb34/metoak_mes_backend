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
 * 物料编码整机工艺关联表
 * </p>
 *
 * @author kevin
 * @since 2025-11-18 19:08:39
 */
@TableName("mo_tag_material_code")
@ApiModel(value = "MoTagMaterialCode对象", description = "物料编码整机工艺关联表")
public class MoTagMaterialCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("物料代码")
    private String materialCode;

    @ApiModelProperty("整机代码")
    private String wholeMachineCode;

    @ApiModelProperty("流水号前缀")
    private String serialPrefix;

    @ApiModelProperty("工艺代码(最后一个字母)")
    private String processCode;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getWholeMachineCode() {
        return wholeMachineCode;
    }

    public void setWholeMachineCode(String wholeMachineCode) {
        this.wholeMachineCode = wholeMachineCode;
    }

    public String getSerialPrefix() {
        return serialPrefix;
    }

    public void setSerialPrefix(String serialPrefix) {
        this.serialPrefix = serialPrefix;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "MoTagMaterialCode{" +
            "id = " + id +
            ", projectName = " + projectName +
            ", materialCode = " + materialCode +
            ", wholeMachineCode = " + wholeMachineCode +
            ", serialPrefix = " + serialPrefix +
            ", processCode = " + processCode +
            ", createdTime = " + createdTime +
            ", updatedTime = " + updatedTime +
        "}";
    }
}
