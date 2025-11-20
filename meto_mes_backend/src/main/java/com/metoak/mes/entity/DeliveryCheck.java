package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@TableName("delivery_check")
@ApiModel(value = "DeliveryCheck对象", description = "")
public class DeliveryCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("taskid")
    private Long taskid;

//    @TableId("sn")
    private String sn;

//    @TableId("stepid")
    private Long stepid;

    private Double distanceTruth;

    private Double distanceMeasured;

    private Double density;

    private Double precision1;

    private Double kpiPrecision;

    private Double kpiDensity;

    private Double lineoffset;

    private Double dispoffset;

    private Long result;

    private LocalDateTime timestamp;

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Long getStepid() {
        return stepid;
    }

    public void setStepid(Long stepid) {
        this.stepid = stepid;
    }

    public Double getDistanceTruth() {
        return distanceTruth;
    }

    public void setDistanceTruth(Double distanceTruth) {
        this.distanceTruth = distanceTruth;
    }

    public Double getDistanceMeasured() {
        return distanceMeasured;
    }

    public void setDistanceMeasured(Double distanceMeasured) {
        this.distanceMeasured = distanceMeasured;
    }

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    public Double getPrecision1() {
        return precision1;
    }

    public void setPrecision1(Double precision1) {
        this.precision1 = precision1;
    }

    public Double getKpiPrecision() {
        return kpiPrecision;
    }

    public void setKpiPrecision(Double kpiPrecision) {
        this.kpiPrecision = kpiPrecision;
    }

    public Double getKpiDensity() {
        return kpiDensity;
    }

    public void setKpiDensity(Double kpiDensity) {
        this.kpiDensity = kpiDensity;
    }

    public Double getLineoffset() {
        return lineoffset;
    }

    public void setLineoffset(Double lineoffset) {
        this.lineoffset = lineoffset;
    }

    public Double getDispoffset() {
        return dispoffset;
    }

    public void setDispoffset(Double dispoffset) {
        this.dispoffset = dispoffset;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "DeliveryCheck{" +
            "taskid = " + taskid +
            ", sn = " + sn +
            ", stepid = " + stepid +
            ", distanceTruth = " + distanceTruth +
            ", distanceMeasured = " + distanceMeasured +
            ", density = " + density +
            ", precision1 = " + precision1 +
            ", kpiPrecision = " + kpiPrecision +
            ", kpiDensity = " + kpiDensity +
            ", lineoffset = " + lineoffset +
            ", dispoffset = " + dispoffset +
            ", result = " + result +
            ", timestamp = " + timestamp +
        "}";
    }
}
