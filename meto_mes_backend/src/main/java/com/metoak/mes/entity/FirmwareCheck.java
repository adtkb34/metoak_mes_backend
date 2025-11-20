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
@TableName("firmware_check")
@ApiModel(value = "FirmwareCheck对象", description = "")
public class FirmwareCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("taskid")
    private Long taskid;

//    @TableId("sn")
    private String sn;

//    @TableId("stepid")
    private Long stepid;

    private Long result;

    private String usbVersion;

    private String fpgaVersion;

    private String usbVersionStandard;

    private String fpgaVersionStandard;

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

    public String getUsbVersion() {
        return usbVersion;
    }

    public void setUsbVersion(String usbVersion) {
        this.usbVersion = usbVersion;
    }

    public String getFpgaVersion() {
        return fpgaVersion;
    }

    public void setFpgaVersion(String fpgaVersion) {
        this.fpgaVersion = fpgaVersion;
    }

    public String getUsbVersionStandard() {
        return usbVersionStandard;
    }

    public void setUsbVersionStandard(String usbVersionStandard) {
        this.usbVersionStandard = usbVersionStandard;
    }

    public String getFpgaVersionStandard() {
        return fpgaVersionStandard;
    }

    public void setFpgaVersionStandard(String fpgaVersionStandard) {
        this.fpgaVersionStandard = fpgaVersionStandard;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "FirmwareCheck{" +
            "taskid = " + taskid +
            ", sn = " + sn +
            ", stepid = " + stepid +
            ", result = " + result +
            ", usbVersion = " + usbVersion +
            ", fpgaVersion = " + fpgaVersion +
            ", usbVersionStandard = " + usbVersionStandard +
            ", fpgaVersionStandard = " + fpgaVersionStandard +
            ", timestamp = " + timestamp +
        "}";
    }
}
