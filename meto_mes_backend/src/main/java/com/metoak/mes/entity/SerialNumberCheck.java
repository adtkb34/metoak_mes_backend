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
@TableName("serial_number_check")
@ApiModel(value = "SerialNumberCheck对象", description = "")
public class SerialNumberCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("taskid")
    private Integer taskid;

//    @TableId("sn")
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
        return "SerialNumberCheck{" +
            "taskid = " + taskid +
            ", sn = " + sn +
            ", result = " + result +
            ", timestamp = " + timestamp +
        "}";
    }
}
