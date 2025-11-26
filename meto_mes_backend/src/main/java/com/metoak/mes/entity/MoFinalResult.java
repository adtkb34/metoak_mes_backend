package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 终检和出货检最终检测结果信息表
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_final_result")
@ApiModel(value = "MoFinalResult对象", description = "终检和出货检最终检测结果信息表")
public class MoFinalResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String cameraSn;

    @ApiModelProperty("工位号")
    private Integer stationNumber;

    @ApiModelProperty("操作人员")
    private String operator;

    @ApiModelProperty("检测时间")
    private LocalDateTime checkTime;

    @ApiModelProperty("检测类型")
    private String checkType;

    @ApiModelProperty("检测结果")
    private Boolean checkResult;

    private Long taskid;

    private Integer errorCode;

    private String imagePath;

    private String toolVersion;

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

    public LocalDateTime getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(LocalDateTime checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public Boolean getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Boolean checkResult) {
        this.checkResult = checkResult;
    }

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getToolVersion() {
        return toolVersion;
    }

    public void setToolVersion(String toolVersion) {
        this.toolVersion = toolVersion;
    }

    @Override
    public String toString() {
        return "MoFinalResult{" +
            "id = " + id +
            ", cameraSn = " + cameraSn +
            ", stationNumber = " + stationNumber +
            ", operator = " + operator +
            ", checkTime = " + checkTime +
            ", checkType = " + checkType +
            ", checkResult = " + checkResult +
            ", taskid = " + taskid +
            ", errorCode = " + errorCode +
            ", imagePath = " + imagePath +
            ", toolVersion = " + toolVersion +
        "}";
    }
}
