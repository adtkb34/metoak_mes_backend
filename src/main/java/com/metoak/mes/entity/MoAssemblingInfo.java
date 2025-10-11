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
 * 
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@TableName("mo_assembling_info")
@ApiModel(value = "MoAssemblingInfo对象", description = "")
public class MoAssemblingInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String beam;

    private String component;

    @ApiModelProperty("1: 横梁号 + pcba;    2: 横梁号 + shell;    5: 横梁号 + 横梁批次;    6: 横梁号 + CMOS批次;   7: 左镜头ID-右镜头ID + shell")
    private Byte componentType;

    private Integer workstation;

    private String operator;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer orderId;

    private Boolean invalid;

    private String toolVersion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBeam() {
        return beam;
    }

    public void setBeam(String beam) {
        this.beam = beam;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Byte getComponentType() {
        return componentType;
    }

    public void setComponentType(Byte componentType) {
        this.componentType = componentType;
    }

    public Integer getWorkstation() {
        return workstation;
    }

    public void setWorkstation(Integer workstation) {
        this.workstation = workstation;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Boolean getInvalid() {
        return invalid;
    }

    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }

    public String getToolVersion() {
        return toolVersion;
    }

    public void setToolVersion(String toolVersion) {
        this.toolVersion = toolVersion;
    }

    @Override
    public String toString() {
        return "MoAssemblingInfo{" +
            "id = " + id +
            ", beam = " + beam +
            ", component = " + component +
            ", componentType = " + componentType +
            ", workstation = " + workstation +
            ", operator = " + operator +
            ", startTime = " + startTime +
            ", endTime = " + endTime +
            ", orderId = " + orderId +
            ", invalid = " + invalid +
            ", toolVersion = " + toolVersion +
        "}";
    }
}
