package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2025-07-01 19:21:55
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_material_binding")
@ApiModel(value = "MoMaterialBinding对象", description = "")
public class MoMaterialBinding implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String cameraSn;

    private String cameraBatch;

    private String category;

    private String categoryNo;

    private String materialBatchNo;

    private String materialSerialNo;

    private Long isDeleted;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long moProcessStepProductionResultId;

    private Integer position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCameraSn() {
        return cameraSn;
    }

    public void setCameraSn(String cameraSn) {
        this.cameraSn = cameraSn;
    }

    public String getCameraBatch() {
        return cameraBatch;
    }

    public void setCameraBatch(String cameraBatch) {
        this.cameraBatch = cameraBatch;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getMaterialBatchNo() {
        return materialBatchNo;
    }

    public void setMaterialBatchNo(String materialBatchNo) {
        this.materialBatchNo = materialBatchNo;
    }

    public String getMaterialSerialNo() {
        return materialSerialNo;
    }

    public void setMaterialSerialNo(String materialSerialNo) {
        this.materialSerialNo = materialSerialNo;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MoMaterialBinding{" +
            "id = " + id +
            ", cameraSn = " + cameraSn +
            ", cameraBatch = " + cameraBatch +
            ", category = " + category +
            ", categoryNo = " + categoryNo +
            ", materialBatchNo = " + materialBatchNo +
            ", materialSerialNo = " + materialSerialNo +
            ", isDeleted = " + isDeleted +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
        "}";
    }
}
