package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2025-07-02 10:06:22
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_process_step_production_result")
@ApiModel(value = "MoProcess＿step＿production＿result对象", description = "")
public class MoProcessStepProductionResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String productSn;

    private String productBatchNo;

    private String stepType;

    private String stepTypeNo;

    private Integer errorCode;

    private String ngReason;

    private Integer stationNum;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String operator;

    private String softwareTool;

    private String softwareToolVersion;

    private Long engineeringParamsId;

    private Long flowParamsId;

    @TableField("param_set_id")
    private String paramSetId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getProductBatchNo() {
        return productBatchNo;
    }

    public void setProductBatchNo(String productBatchNo) {
        this.productBatchNo = productBatchNo;
    }

    public String getStepType() {
        return stepType;
    }

    public void setStepType(String stepType) {
        this.stepType = stepType;
    }

    public String getStepTypeNo() {
        return stepTypeNo;
    }

    public void setStepTypeNo(String stepTypeNo) {
        this.stepTypeNo = stepTypeNo;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getNgReason() {
        return ngReason;
    }

    public void setNgReason(String ngReason) {
        this.ngReason = ngReason;
    }

    public Integer getStationNum() {
        return stationNum;
    }

    public void setStationNum(Integer stationNum) {
        this.stationNum = stationNum;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSoftwareTool() {
        return softwareTool;
    }

    public void setSoftwareTool(String softwareTool) {
        this.softwareTool = softwareTool;
    }

    public String getSoftwareToolVersion() {
        return softwareToolVersion;
    }

    public void setSoftwareToolVersion(String softwareToolVersion) {
        this.softwareToolVersion = softwareToolVersion;
    }

    public String getParamSetId() {
        return paramSetId;
    }

    public void setParamSetId(String paramSetId) {
        this.paramSetId = paramSetId;
    }

    @Override
    public String toString() {
        return "MoProcess＿step＿production＿result{" +
            "id = " + id +
            ", productSn = " + productSn +
            ", productBatchNo = " + productBatchNo +
            ", step＿type = " + stepType +
            ", step＿type＿no = " + stepTypeNo +
            ", errorCode = " + errorCode +
            ", ngReason = " + ngReason +
            ", stationNum = " + stationNum +
            ", startTime = " + startTime +
            ", endTime = " + endTime +
            ", operator = " + operator +
            ", softwareTool = " + softwareTool +
            ", softwareToolVersion = " + softwareToolVersion +
            ", paramSetId = " + paramSetId +
        "}";
    }
}
