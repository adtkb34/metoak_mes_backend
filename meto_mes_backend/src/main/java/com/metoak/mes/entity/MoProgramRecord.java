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
 * 程序记录表
 * </p>
 *
 * @author kevin
 * @since 2025-02-11 10:00:00
 */
@TableName("mo_program_record")
@ApiModel(value = "MoProgramRecord对象", description = "程序记录表")
public class MoProgramRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty("错误码")
    private Integer errorCode;

    @ApiModelProperty("操作员")
    private String operator;

    @ApiModelProperty("参数标识")
    private Long paramsId;

    @ApiModelProperty("软件工具名称")
    private String softwareTool;

    @ApiModelProperty("软件工具版本")
    private String softwareToolVersion;

    @ApiModelProperty("固件名称")
    private String firmwareName;

    @ApiModelProperty("固件类型")
    private Byte firmwareType;

    @ApiModelProperty("固件版本")
    private String firmwareVersion;

    @ApiModelProperty("SN类型")
    private Integer snType;

    @ApiModelProperty("工单号")
    private String workOrderId;

    @ApiModelProperty("物料编码")
    private String materialCode;

    @ApiModelProperty("序列号")
    private String sn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Long getParamsId() {
        return paramsId;
    }

    public void setParamsId(Long paramsId) {
        this.paramsId = paramsId;
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

    public String getFirmwareName() {
        return firmwareName;
    }

    public void setFirmwareName(String firmwareName) {
        this.firmwareName = firmwareName;
    }

    public Byte getFirmwareType() {
        return firmwareType;
    }

    public void setFirmwareType(Byte firmwareType) {
        this.firmwareType = firmwareType;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public Integer getSnType() {
        return snType;
    }

    public void setSnType(Integer snType) {
        this.snType = snType;
    }

    public String getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(String workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    public String toString() {
        return "MoProgramRecord{" +
            "id = " + id +
            ", startTime = " + startTime +
            ", endTime = " + endTime +
            ", errorCode = " + errorCode +
            ", operator = '" + operator + '\'' +
            ", paramsId = " + paramsId +
            ", softwareTool = '" + softwareTool + '\'' +
            ", softwareToolVersion = '" + softwareToolVersion + '\'' +
            ", firmwareName = '" + firmwareName + '\'' +
            ", firmwareType = " + firmwareType +
            ", firmwareVersion = '" + firmwareVersion + '\'' +
            ", snType = " + snType +
            ", workOrderId = '" + workOrderId + '\'' +
            ", materialCode = '" + materialCode + '\'' +
            ", sn = '" + sn + '\'' +
        "}";
    }
}
