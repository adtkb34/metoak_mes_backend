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
 * 物料批次追踪表
 * </p>
 *
 * @author kevin
 * @since 2025-08-11 15:34:00
 */
@TableName("mo_lens_material_trace")
@ApiModel(value = "MoLensMaterialTrace对象", description = "物料批次追踪表")
public class MoLensMaterialTrace implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String workOrderNo;

    private Integer sequence;

    private String updateCount;

    private String categoryNo;

    private Integer position;

    private String batchNo;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkOrderNo() {
        return workOrderNo;
    }

    public void setWorkOrderNo(String workOrderNo) {
        this.workOrderNo = workOrderNo;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(String updateCount) {
        this.updateCount = updateCount;
    }

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "MoLensMaterialTrace{" +
            "id = " + id +
            ", workOrderNo = " + workOrderNo +
            ", sequence = " + sequence +
            ", updateCount = " + updateCount +
            ", categoryNo = " + categoryNo +
            ", position = " + position +
            ", batchNo = " + batchNo +
            ", createdAt = " + createdAt +
        "}";
    }
}
