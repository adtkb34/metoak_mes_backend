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
 * CMOS 外观检测数据
 * </p>
 *
 * @author kevin
 * @since 2025-07-04 16:08:02
 */
@TableName("mo_cmos_appearance_inspection")
@ApiModel(value = "MoCmosAppearanceInspection对象", description = "CMOS 外观检测数据")
public class MoCmosAppearanceInspection implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @FieldCode(no = "000", type = "val", label = "Sensor 平行度")
    @ApiModelProperty("Sensor 平行度")
    private BigDecimal sensorParallelism;

    @FieldCode(no = "001", type = "val", label = "两孔间距")
    @ApiModelProperty("两孔间距")
    private BigDecimal holeDistance;

    @FieldCode(no = "001", type = "usl", label = "两孔间距上限")
    @ApiModelProperty("两孔间距")
    private BigDecimal holeDistanceUsl;

    @FieldCode(no = "001", type = "lsl", label = "两孔间距下限")
    @ApiModelProperty("两孔间距")
    private BigDecimal holeDistanceLsl;

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

    public BigDecimal getSensorParallelism() {
        return sensorParallelism;
    }

    public void setSensorParallelism(BigDecimal sensorParallelism) {
        this.sensorParallelism = sensorParallelism;
    }

    public BigDecimal getHoleDistance() {
        return holeDistance;
    }

    public void setHoleDistance(BigDecimal holeDistance) {
        this.holeDistance = holeDistance;
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
        return "MoCmosAppearanceInspection{" +
            "id = " + id +
            ", sensorParallelism = " + sensorParallelism +
            ", holeDistance = " + holeDistance +
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
