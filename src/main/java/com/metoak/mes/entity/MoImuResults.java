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
@TableName("mo_imu_results")
@ApiModel(value = "MoImuResults对象", description = "")
public class MoImuResults implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String cameraSn;

    private Double rRoll;

    private Double rPitch;

    private Double rYaw;

    private Double tX;

    private Double tY;

    private Double tZ;

    private Double accelerometerErrorMean;

    private Double accelerometerErrorStd;

    private Double gyroscopeErrorMean;

    private Double gyroscopeErrorStd;

    private Double reprojectionErrorMean;

    private Double reprojectionErrorStd;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDelete;

    private Long calibresultId;

    private Integer position;

    private Double accelerometerNoiseDensity;

    private Double accelerometerRandomWalk;

    private Double gyroscopeNoiseDensity;

    private Double gyroscopeRandomWalk;

    private Integer status;

    private String errorNo;

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

    public Double getrRoll() {
        return rRoll;
    }

    public void setrRoll(Double rRoll) {
        this.rRoll = rRoll;
    }

    public Double getrPitch() {
        return rPitch;
    }

    public void setrPitch(Double rPitch) {
        this.rPitch = rPitch;
    }

    public Double getrYaw() {
        return rYaw;
    }

    public void setrYaw(Double rYaw) {
        this.rYaw = rYaw;
    }

    public Double gettX() {
        return tX;
    }

    public void settX(Double tX) {
        this.tX = tX;
    }

    public Double gettY() {
        return tY;
    }

    public void settY(Double tY) {
        this.tY = tY;
    }

    public Double gettZ() {
        return tZ;
    }

    public void settZ(Double tZ) {
        this.tZ = tZ;
    }

    public Double getAccelerometerErrorMean() {
        return accelerometerErrorMean;
    }

    public void setAccelerometerErrorMean(Double accelerometerErrorMean) {
        this.accelerometerErrorMean = accelerometerErrorMean;
    }

    public Double getAccelerometerErrorStd() {
        return accelerometerErrorStd;
    }

    public void setAccelerometerErrorStd(Double accelerometerErrorStd) {
        this.accelerometerErrorStd = accelerometerErrorStd;
    }

    public Double getGyroscopeErrorMean() {
        return gyroscopeErrorMean;
    }

    public void setGyroscopeErrorMean(Double gyroscopeErrorMean) {
        this.gyroscopeErrorMean = gyroscopeErrorMean;
    }

    public Double getGyroscopeErrorStd() {
        return gyroscopeErrorStd;
    }

    public void setGyroscopeErrorStd(Double gyroscopeErrorStd) {
        this.gyroscopeErrorStd = gyroscopeErrorStd;
    }

    public Double getReprojectionErrorMean() {
        return reprojectionErrorMean;
    }

    public void setReprojectionErrorMean(Double reprojectionErrorMean) {
        this.reprojectionErrorMean = reprojectionErrorMean;
    }

    public Double getReprojectionErrorStd() {
        return reprojectionErrorStd;
    }

    public void setReprojectionErrorStd(Double reprojectionErrorStd) {
        this.reprojectionErrorStd = reprojectionErrorStd;
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

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Long getCalibresultId() {
        return calibresultId;
    }

    public void setCalibresultId(Long calibresultId) {
        this.calibresultId = calibresultId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Double getAccelerometerNoiseDensity() {
        return accelerometerNoiseDensity;
    }

    public void setAccelerometerNoiseDensity(Double accelerometerNoiseDensity) {
        this.accelerometerNoiseDensity = accelerometerNoiseDensity;
    }

    public Double getAccelerometerRandomWalk() {
        return accelerometerRandomWalk;
    }

    public void setAccelerometerRandomWalk(Double accelerometerRandomWalk) {
        this.accelerometerRandomWalk = accelerometerRandomWalk;
    }

    public Double getGyroscopeNoiseDensity() {
        return gyroscopeNoiseDensity;
    }

    public void setGyroscopeNoiseDensity(Double gyroscopeNoiseDensity) {
        this.gyroscopeNoiseDensity = gyroscopeNoiseDensity;
    }

    public Double getGyroscopeRandomWalk() {
        return gyroscopeRandomWalk;
    }

    public void setGyroscopeRandomWalk(Double gyroscopeRandomWalk) {
        this.gyroscopeRandomWalk = gyroscopeRandomWalk;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }

    @Override
    public String toString() {
        return "MoImuResults{" +
            "id = " + id +
            ", cameraSn = " + cameraSn +
            ", rRoll = " + rRoll +
            ", rPitch = " + rPitch +
            ", rYaw = " + rYaw +
            ", tX = " + tX +
            ", tY = " + tY +
            ", tZ = " + tZ +
            ", accelerometerErrorMean = " + accelerometerErrorMean +
            ", accelerometerErrorStd = " + accelerometerErrorStd +
            ", gyroscopeErrorMean = " + gyroscopeErrorMean +
            ", gyroscopeErrorStd = " + gyroscopeErrorStd +
            ", reprojectionErrorMean = " + reprojectionErrorMean +
            ", reprojectionErrorStd = " + reprojectionErrorStd +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", isDelete = " + isDelete +
            ", calibresultId = " + calibresultId +
            ", position = " + position +
            ", accelerometerNoiseDensity = " + accelerometerNoiseDensity +
            ", accelerometerRandomWalk = " + accelerometerRandomWalk +
            ", gyroscopeNoiseDensity = " + gyroscopeNoiseDensity +
            ", gyroscopeRandomWalk = " + gyroscopeRandomWalk +
            ", status = " + status +
            ", errorNo = " + errorNo +
        "}";
    }
}
