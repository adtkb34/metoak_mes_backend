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
 * 高温固化工序记录表
 * </p>
 *
 * @author kevin
 * @since 2025-07-04 16:08:02
 */
@TableName("mo_high_temp_curing_record")
@ApiModel(value = "MoHighTempCuringRecord对象", description = "高温固化工序记录表")
public class MoHighTempCuringRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @FieldCode(no = "000", type = "val", label = "料盘号")
    @ApiModelProperty("料盘号")
    private String trayNo;

    @FieldCode(no = "001", type = "val", label = "炉号")
    @ApiModelProperty("炉号")
    private String ovenNo;

    @FieldCode(no = "002", type = "val", label = "最低炉温 (°C)")
    @ApiModelProperty("最低炉温 (°C)")
    private BigDecimal minTemp;

    @FieldCode(no = "003", type = "val", label = "最高炉温 (°C)")
    @ApiModelProperty("最高炉温 (°C)")
    private BigDecimal maxTemp;

    @FieldCode(no = "004", type = "val", label = "烘烤时间（分钟）")
    @ApiModelProperty("烘烤时间（分钟）")
    private Integer bakeDurationMinutes;

    /* =========== 005 温度达到时间 =========== */
    @FieldCode(no = "005", type = "val", label = "温度达到时间")
    private String temperatureReachingTime;

    /* =========== 006 烘烤完成时间 =========== */
    @FieldCode(no = "006", type = "val", label = "烘烤完成时间")
    private String bakingEndTime;

    @FieldCode(no = "007", type = "val", label = "夹爪编号")
    private String gripperNumber;

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

    public String getTrayNo() {
        return trayNo;
    }

    public void setTrayNo(String trayNo) {
        this.trayNo = trayNo;
    }

    public String getOvenNo() {
        return ovenNo;
    }

    public void setOvenNo(String ovenNo) {
        this.ovenNo = ovenNo;
    }

    public BigDecimal getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(BigDecimal minTemp) {
        this.minTemp = minTemp;
    }

    public BigDecimal getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(BigDecimal maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Integer getBakeDurationMinutes() {
        return bakeDurationMinutes;
    }

    public void setBakeDurationMinutes(Integer bakeDurationMinutes) {
        this.bakeDurationMinutes = bakeDurationMinutes;
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
        return "MoHighTempCuringRecord{" +
            "id = " + id +
            ", trayNo = " + trayNo +
            ", ovenNo = " + ovenNo +
            ", minTemp = " + minTemp +
            ", maxTemp = " + maxTemp +
            ", bakeDurationMinutes = " + bakeDurationMinutes +
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
