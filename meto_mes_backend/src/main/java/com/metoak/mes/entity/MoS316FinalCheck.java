package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 生产结果记录表
 * </p>
 *
 * @author kevin
 * @since 2024-11-16 14:52:00
 */
@TableName("mo_s316_final_check")
@ApiModel(value = "MoS316FinalCheck对象", description = "生产结果记录表")
public class MoS316FinalCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("【过站表】记录关联ID。通用字段在过站表里面：数据库表单-过站信息")
    private Long moProcessStepProductionResultId;

    @ApiModelProperty("相机序列号")
    private String sn;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty("错误码")
    private Integer errcode;

    @ApiModelProperty("是否为 OQC 记录")
    private Boolean isOqc;

    @ApiModelProperty("产品类别")
    private Integer productType;

    @ApiModelProperty("是否签名")
    private Boolean isSigned;

    @ApiModelProperty("MCU 版本")
    private String versionMcu;

    private Float imuTx;

    private Float imuTy;

    private Float imuTz;

    @ApiModelProperty("IMU qAngle")
    private Float imuQangleDeg;

    @ApiModelProperty("距离1的测距误差")
    private Float distance1Error;

    @ApiModelProperty("距离1的视差稠密度")
    private Float distance1Density;

    @ApiModelProperty("距离2的测距误差")
    private Float distance2Error;

    @ApiModelProperty("距离2的视差稠密度")
    private Float distance2Density;

    @ApiModelProperty("相关素材的存储根路径")
    private String materialRoot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMoProcessStepProductionResultId() {
        return moProcessStepProductionResultId;
    }

    public void setMoProcessStepProductionResultId(Long moProcessStepProductionResultId) {
        this.moProcessStepProductionResultId = moProcessStepProductionResultId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public Boolean getIsOqc() {
        return isOqc;
    }

    public void setIsOqc(Boolean isOqc) {
        this.isOqc = isOqc;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Boolean getIsSigned() {
        return isSigned;
    }

    public void setIsSigned(Boolean isSigned) {
        this.isSigned = isSigned;
    }

    public String getVersionMcu() {
        return versionMcu;
    }

    public void setVersionMcu(String versionMcu) {
        this.versionMcu = versionMcu;
    }

    public Float getImuTx() {
        return imuTx;
    }

    public void setImuTx(Float imuTx) {
        this.imuTx = imuTx;
    }

    public Float getImuTy() {
        return imuTy;
    }

    public void setImuTy(Float imuTy) {
        this.imuTy = imuTy;
    }

    public Float getImuTz() {
        return imuTz;
    }

    public void setImuTz(Float imuTz) {
        this.imuTz = imuTz;
    }

    public Float getImuQangleDeg() {
        return imuQangleDeg;
    }

    public void setImuQangleDeg(Float imuQangleDeg) {
        this.imuQangleDeg = imuQangleDeg;
    }

    public Float getDistance1Error() {
        return distance1Error;
    }

    public void setDistance1Error(Float distance1Error) {
        this.distance1Error = distance1Error;
    }

    public Float getDistance1Density() {
        return distance1Density;
    }

    public void setDistance1Density(Float distance1Density) {
        this.distance1Density = distance1Density;
    }

    public Float getDistance2Error() {
        return distance2Error;
    }

    public void setDistance2Error(Float distance2Error) {
        this.distance2Error = distance2Error;
    }

    public Float getDistance2Density() {
        return distance2Density;
    }

    public void setDistance2Density(Float distance2Density) {
        this.distance2Density = distance2Density;
    }

    public String getMaterialRoot() {
        return materialRoot;
    }

    public void setMaterialRoot(String materialRoot) {
        this.materialRoot = materialRoot;
    }

    @Override
    public String toString() {
        return "MoS316FinalCheck{" +
                "id=" + id +
                ", moProcessStepProductionResultId=" + moProcessStepProductionResultId +
                ", sn='" + sn + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", errcode=" + errcode +
                ", isOqc=" + isOqc +
                ", productType=" + productType +
                ", isSigned=" + isSigned +
                ", versionMcu='" + versionMcu + '\'' +
                ", imuTx=" + imuTx +
                ", imuTy=" + imuTy +
                ", imuTz=" + imuTz +
                ", imuQangleDeg=" + imuQangleDeg +
                ", distance1Error=" + distance1Error +
                ", distance1Density=" + distance1Density +
                ", distance2Error=" + distance2Error +
                ", distance2Density=" + distance2Density +
                ", materialRoot='" + materialRoot + '\'' +
                '}';
    }
}
