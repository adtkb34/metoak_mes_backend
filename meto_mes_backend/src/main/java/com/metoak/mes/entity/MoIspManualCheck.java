package com.metoak.mes.entity;

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
@TableName("mo_isp_manual_check")
@ApiModel(value = "MoIspManualCheck对象", description = "")
public class MoIspManualCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("taskid")
    private Integer taskid;

    private String sn;

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
        return "MoIspManualCheck{" +
            "taskid = " + taskid +
            ", sn = " + sn +
            ", result = " + result +
            ", timestamp = " + timestamp +
        "}";
    }
}
