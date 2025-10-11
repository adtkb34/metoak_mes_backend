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
 * 出货检验信息表
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@TableName("mo_oqc_info")
@ApiModel(value = "MoOqcInfo对象", description = "出货检验信息表")
public class MoOqcInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    @ApiModelProperty("检测结果")
    private Boolean checkResult;

    private String fileName;

    private String imagePath;

    @ApiModelProperty("时间戳标记")
    private Integer timestampTag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getTimestampTag() {
        return timestampTag;
    }

    public void setTimestampTag(Integer timestampTag) {
        this.timestampTag = timestampTag;
    }

    @Override
    public String toString() {
        return "MoOqcInfo{" +
            "id = " + id +
            ", cameraSn = " + cameraSn +
            ", stationNumber = " + stationNumber +
            ", operator = " + operator +
            ", startTime = " + startTime +
            ", endTime = " + endTime +
            ", stepid = " + stepid +
            ", checkResult = " + checkResult +
            ", fileName = " + fileName +
            ", imagePath = " + imagePath +
            ", timestampTag = " + timestampTag +
        "}";
    }
}
