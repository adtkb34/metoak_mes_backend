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
 * 终检信息表
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@TableName("mo_final_check")
@ApiModel(value = "MoFinalCheck对象", description = "终检信息表")
public class MoFinalCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String cameraSn;

    @ApiModelProperty("工位号")
    private Integer stationNumber;

    @ApiModelProperty("操作人员")
    private String operator;

    @ApiModelProperty("工序开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("工序结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty("检查步骤序号")
    private Integer stepid;

    @ApiModelProperty("检查结果")
    private Boolean checkResult;

    private String imagePath;

    private String fileName;

    private Long taskid;

    private Long timestampTag;

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

    public Integer getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(Integer stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    public Integer getStepid() {
        return stepid;
    }

    public void setStepid(Integer stepid) {
        this.stepid = stepid;
    }

    public Boolean getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Boolean checkResult) {
        this.checkResult = checkResult;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }

    public Long getTimestampTag() {
        return timestampTag;
    }

    public void setTimestampTag(Long timestampTag) {
        this.timestampTag = timestampTag;
    }

    @Override
    public String toString() {
        return "MoFinalCheck{" +
            "id = " + id +
            ", cameraSn = " + cameraSn +
            ", stationNumber = " + stationNumber +
            ", operator = " + operator +
            ", startTime = " + startTime +
            ", endTime = " + endTime +
            ", stepid = " + stepid +
            ", checkResult = " + checkResult +
            ", imagePath = " + imagePath +
            ", fileName = " + fileName +
            ", taskid = " + taskid +
            ", timestampTag = " + timestampTag +
        "}";
    }
}
