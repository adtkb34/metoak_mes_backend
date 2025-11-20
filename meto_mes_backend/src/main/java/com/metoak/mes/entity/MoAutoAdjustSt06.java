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
 * 
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@TableName("mo_auto_adjust_st06")
@ApiModel(value = "MoAutoAdjustSt06对象", description = "")
public class MoAutoAdjustSt06 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String beamSn;

    private LocalDateTime operationTime;

    private Boolean result;

    private String ngReason;

    private String imagePath;

    private String side;

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

    public LocalDateTime getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(LocalDateTime operationTime) {
        this.operationTime = operationTime;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getNgReason() {
        return ngReason;
    }

    public void setNgReason(String ngReason) {
        this.ngReason = ngReason;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return "MoAutoAdjustSt06{" +
            "id = " + id +
            ", beamSn = " + beamSn +
            ", operationTime = " + operationTime +
            ", result = " + result +
            ", ngReason = " + ngReason +
            ", imagePath = " + imagePath +
            ", side = " + side +
        "}";
    }
}
