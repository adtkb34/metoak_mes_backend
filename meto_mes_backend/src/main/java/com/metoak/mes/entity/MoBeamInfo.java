package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 横梁序列号信息表
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_beam_info")
@ApiModel(value = "MoBeamInfo对象", description = "横梁序列号信息表")
public class MoBeamInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String beamSn;

    private String workOrderCode;

    private Integer serialNumber;

    private LocalDateTime createTime;

    private Boolean isProduced;

    private Integer produceOrderId;

    private Integer isUsed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBeamSn() {
        return beamSn;
    }

    public void setBeamSn(String beamSn) {
        this.beamSn = beamSn;
    }

    public String getWorkOrderCode() {
        return workOrderCode;
    }

    public void setWorkOrderCode(String workOrderCode) {
        this.workOrderCode = workOrderCode;
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

    public Boolean getIsProduced() {
        return isProduced;
    }

    public void setIsProduced(Boolean isProduced) {
        this.isProduced = isProduced;
    }

    public Integer getProduceOrderId() {
        return produceOrderId;
    }

    public void setProduceOrderId(Integer produceOrderId) {
        this.produceOrderId = produceOrderId;
    }

    @Override
    public String toString() {
        return "MoBeamInfo{" +
            "id = " + id +
            ", beamSn = " + beamSn +
            ", workOrderCode = " + workOrderCode +
            ", serialNumber = " + serialNumber +
            ", createTime = " + createTime +
            ", isProduced = " + isProduced +
            ", produceOrderId = " + produceOrderId +
        "}";
    }
}
