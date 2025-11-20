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
@TableName("simor_param_check")
@ApiModel(value = "SimorParamCheck对象", description = "")
public class SimorParamCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("taskid")
    private Integer taskid;

//    @TableId("sn")
    private String sn;

//    @TableId("stepid")
    private Integer stepid;

    private Integer result;

    private LocalDateTime timestamp;

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getStepid() {
        return stepid;
    }

    public void setStepid(Integer stepid) {
        this.stepid = stepid;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
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
        return "SimorParamCheck{" +
            "taskid = " + taskid +
            ", sn = " + sn +
            ", stepid = " + stepid +
            ", result = " + result +
            ", timestamp = " + timestamp +
        "}";
    }
}
