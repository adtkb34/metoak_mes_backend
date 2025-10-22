package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.metoak.mes.common.annotate.FieldCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 涂布工序记录表
 * </p>
 *
 * @author kevin
 * @since 2025-07-04 16:08:02
 */
@TableName("mo_after_aa_coating_process_record")
@ApiModel(value = "MoAfterAaCoatingProcessRecord对象", description = "涂布工序记录表")
public class MoAfterAaCoatingProcessRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @FieldCode(no = "000", type = "spec", label = "喷涂压力 (bar 或 MPa)")
    @ApiModelProperty("喷涂压力 (bar 或 MPa)")
    private BigDecimal sprayPressure;

    @FieldCode(no = "001", type = "spec", label = "喷涂轨迹程序编号")
    @ApiModelProperty("喷涂轨迹程序编号")
    private String sprayProgramCode;

    private Long moProcessStepProductionResultId;

    private Integer errorCode;

    private Integer stationNum;

    private String ngReason;

    private LocalDateTime addTime;

    private String beamSn;

    private String position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSprayPressure() {
        return sprayPressure;
    }

    public void setSprayPressure(BigDecimal sprayPressure) {
        this.sprayPressure = sprayPressure;
    }

    public String getSprayProgramCode() {
        return sprayProgramCode;
    }

    public void setSprayProgramCode(String sprayProgramCode) {
        this.sprayProgramCode = sprayProgramCode;
    }

    public Long getMoProcessStepProductionResultId() {
        return moProcessStepProductionResultId;
    }

    public void setMoProcessStepProductionResultId(Long moProcessStepProductionResultId) {
        this.moProcessStepProductionResultId = moProcessStepProductionResultId;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getStationNum() {
        return stationNum;
    }

    public void setStationNum(Integer stationNum) {
        this.stationNum = stationNum;
    }

    public String getNgReason() {
        return ngReason;
    }

    public void setNgReason(String ngReason) {
        this.ngReason = ngReason;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public String getBeamSn() {
        return beamSn;
    }

    public void setBeamSn(String beamSn) {
        this.beamSn = beamSn;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "MoAfterAaCoatingProcessRecord{" +
            "id = " + id +
            ", sprayPressure = " + sprayPressure +
            ", sprayProgramCode = " + sprayProgramCode +
            ", moProcessStepProductionResultId = " + moProcessStepProductionResultId +
            ", errorCode = " + errorCode +
            ", stationNum = " + stationNum +
            ", ngReason = " + ngReason +
            ", addTime = " + addTime +
            ", beamSn = " + beamSn +
            ", position = " + position +
        "}";
    }
}
