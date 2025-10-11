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
@TableName("isp_param_check")
@ApiModel(value = "IspParamCheck对象", description = "")
public class IspParamCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("taskid")
    private Long taskid;

//    @TableId("sn")
    private String sn;

//    @TableId("stepid")
    private Long stepid;

    private Long result;

    private Double lo;

    private Double disp;

    private Double kpiLo;

    private Double kpiDisp;

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

    public Double getLo() {
        return lo;
    }

    public void setLo(Double lo) {
        this.lo = lo;
    }

    public Double getDisp() {
        return disp;
    }

    public void setDisp(Double disp) {
        this.disp = disp;
    }

    public Double getKpiLo() {
        return kpiLo;
    }

    public void setKpiLo(Double kpiLo) {
        this.kpiLo = kpiLo;
    }

    public Double getKpiDisp() {
        return kpiDisp;
    }

    public void setKpiDisp(Double kpiDisp) {
        this.kpiDisp = kpiDisp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "IspParamCheck{" +
            "taskid = " + taskid +
            ", sn = " + sn +
            ", stepid = " + stepid +
            ", result = " + result +
            ", lo = " + lo +
            ", disp = " + disp +
            ", kpiLo = " + kpiLo +
            ", kpiDisp = " + kpiDisp +
            ", timestamp = " + timestamp +
        "}";
    }
}
