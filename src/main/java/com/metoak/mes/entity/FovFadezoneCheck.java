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
@TableName("fov_fadezone_check")
@ApiModel(value = "FovFadezoneCheck对象", description = "")
public class FovFadezoneCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("taskid")
    private Long taskid;

//    @TableId("sn")
    private String sn;

//    @TableId("stepid")
    private Long stepid;

    private Long result;

    private Long fov;

    private Double densityFadezone;

    private Double kpiFadezone;

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

    public Long getFov() {
        return fov;
    }

    public void setFov(Long fov) {
        this.fov = fov;
    }

    public Double getDensityFadezone() {
        return densityFadezone;
    }

    public void setDensityFadezone(Double densityFadezone) {
        this.densityFadezone = densityFadezone;
    }

    public Double getKpiFadezone() {
        return kpiFadezone;
    }

    public void setKpiFadezone(Double kpiFadezone) {
        this.kpiFadezone = kpiFadezone;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "FovFadezoneCheck{" +
            "taskid = " + taskid +
            ", sn = " + sn +
            ", stepid = " + stepid +
            ", result = " + result +
            ", fov = " + fov +
            ", densityFadezone = " + densityFadezone +
            ", kpiFadezone = " + kpiFadezone +
            ", timestamp = " + timestamp +
        "}";
    }
}
