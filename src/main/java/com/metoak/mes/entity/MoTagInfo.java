package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 标签序列号表
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_tag_info")
@ApiModel(value = "MoTagInfo对象", description = "标签序列号表")
public class MoTagInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String tagSn;

    private String workOrderCode;

    private String frontSection;

    private Integer serialNumber;

    private LocalDateTime createTime;

    private Integer produceOrderId;

    private String operator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagSn() {
        return tagSn;
    }

    public void setTagSn(String tagSn) {
        this.tagSn = tagSn;
    }

    public String getWorkOrderCode() {
        return workOrderCode;
    }

    public void setWorkOrderCode(String workOrderCode) {
        this.workOrderCode = workOrderCode;
    }

    public String getFrontSection() {
        return frontSection;
    }

    public void setFrontSection(String frontSection) {
        this.frontSection = frontSection;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getProduceOrderId() {
        return produceOrderId;
    }

    public void setProduceOrderId(Integer produceOrderId) {
        this.produceOrderId = produceOrderId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "MoTagInfo{" +
            "id = " + id +
            ", tagSn = " + tagSn +
            ", workOrderCode = " + workOrderCode +
            ", frontSection = " + frontSection +
            ", serialNumber = " + serialNumber +
            ", createTime = " + createTime +
            ", produceOrderId = " + produceOrderId +
            ", operator = " + operator +
        "}";
    }
}
