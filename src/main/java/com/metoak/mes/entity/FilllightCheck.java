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
@TableName("filllight_check")
@ApiModel(value = "FilllightCheck对象", description = "")
public class FilllightCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("taskid")
    private Long taskid;

//    @TableId("sn")
    private String sn;

//    @TableId("stepid")
    private Long stepid;

    private Long result;

    private Long fillLightStatus;

    private Double densityTarget;

    private Double kpiDensityTarget;

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

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public Long getFillLightStatus() {
        return fillLightStatus;
    }

    public void setFillLightStatus(Long fillLightStatus) {
        this.fillLightStatus = fillLightStatus;
    }

    public Double getDensityTarget() {
        return densityTarget;
    }

    public void setDensityTarget(Double densityTarget) {
        this.densityTarget = densityTarget;
    }

    public Double getKpiDensityTarget() {
        return kpiDensityTarget;
    }

    public void setKpiDensityTarget(Double kpiDensityTarget) {
        this.kpiDensityTarget = kpiDensityTarget;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "FilllightCheck{" +
            "taskid = " + taskid +
            ", sn = " + sn +
            ", stepid = " + stepid +
            ", result = " + result +
            ", fillLightStatus = " + fillLightStatus +
            ", densityTarget = " + densityTarget +
            ", kpiDensityTarget = " + kpiDensityTarget +
            ", timestamp = " + timestamp +
        "}";
    }
}
