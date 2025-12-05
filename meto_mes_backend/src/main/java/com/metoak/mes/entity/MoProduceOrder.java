package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 生产工单信息表
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_produce_order")
@ApiModel(value = "MoProduceOrder对象", description = "生产工单信息表")
public class MoProduceOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("工单编号")
    private String workOrderCode;

    @ApiModelProperty("单据日期")
    private LocalDate orderDate;

    @ApiModelProperty("物料编号")
    private String materialCode;

    @ApiModelProperty("物料名称")
    private String materialName;

    @ApiModelProperty("规格型号")
    private String modelType;

    @ApiModelProperty("生产车间")
    private String workshop;

    @ApiModelProperty("计划生产数量")
    private Integer produceCount;

    @ApiModelProperty("产品单位")
    private String produceUnit;

    @ApiModelProperty("计划开工时间")
    private LocalDateTime plannedStarttime;

    @ApiModelProperty("计划完工时间")
    private LocalDateTime plannedEndtime;

    private LocalDateTime addedTime;

    @ApiModelProperty("工艺流程编号")
    private String flowCode;

    private Integer flowAssembleShell;

    @ApiModelProperty("BOM版本")
    private String bomVersion;

    @ApiModelProperty("排产状态")
    private String scheduleState;

    @ApiModelProperty("CMOS批号")
    private String cmosPn;

    @ApiModelProperty("镜头批号")
    private String lensSn;

    @ApiModelProperty("FPGA版本号")
    private String fpgaVersion;

    @ApiModelProperty("工单状态")
    private Integer orderState;

    private Integer completedCount;

    private String productType;

    @ApiModelProperty("是否允许 SN 超过计划数量：0=否，1=是")
    private Integer tagSnCanExceedPlannedCount;

    @ApiModelProperty("参数集详情ID")
    private Long paramsDetailId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkOrderCode() {
        return workOrderCode;
    }

    public void setWorkOrderCode(String workOrderCode) {
        this.workOrderCode = workOrderCode;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public Integer getProduceCount() {
        return produceCount;
    }

    public void setProduceCount(Integer produceCount) {
        this.produceCount = produceCount;
    }

    public String getProduceUnit() {
        return produceUnit;
    }

    public void setProduceUnit(String produceUnit) {
        this.produceUnit = produceUnit;
    }

    public LocalDateTime getPlannedStarttime() {
        return plannedStarttime;
    }

    public void setPlannedStarttime(LocalDateTime plannedStarttime) {
        this.plannedStarttime = plannedStarttime;
    }

    public LocalDateTime getPlannedEndtime() {
        return plannedEndtime;
    }

    public void setPlannedEndtime(LocalDateTime plannedEndtime) {
        this.plannedEndtime = plannedEndtime;
    }

    public LocalDateTime getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(LocalDateTime addedTime) {
        this.addedTime = addedTime;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public Integer getFlowAssembleShell() {
        return flowAssembleShell;
    }

    public void setFlowAssembleShell(Integer flowAssembleShell) {
        this.flowAssembleShell = flowAssembleShell;
    }

    public String getBomVersion() {
        return bomVersion;
    }

    public void setBomVersion(String bomVersion) {
        this.bomVersion = bomVersion;
    }

    public String getScheduleState() {
        return scheduleState;
    }

    public void setScheduleState(String scheduleState) {
        this.scheduleState = scheduleState;
    }

    public String getCmosPn() {
        return cmosPn;
    }

    public void setCmosPn(String cmosPn) {
        this.cmosPn = cmosPn;
    }

    public String getLensSn() {
        return lensSn;
    }

    public void setLensSn(String lensSn) {
        this.lensSn = lensSn;
    }

    public String getFpgaVersion() {
        return fpgaVersion;
    }

    public void setFpgaVersion(String fpgaVersion) {
        this.fpgaVersion = fpgaVersion;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(Integer completedCount) {
        this.completedCount = completedCount;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getTagSnCanExceedPlannedCount() {
        return tagSnCanExceedPlannedCount;
    }

    public void setTagSnCanExceedPlannedCount(Integer tagSnCanExceedPlannedCount) {
        this.tagSnCanExceedPlannedCount = tagSnCanExceedPlannedCount;
    }

    public Long getParamsDetailId() {
        return paramsDetailId;
    }

    public void setParamsDetailId(Long paramsDetailId) {
        this.paramsDetailId = paramsDetailId;
    }

    @Override
    public String toString() {
        return "MoProduceOrder{" +
            "id = " + id +
            ", workOrderCode = " + workOrderCode +
            ", orderDate = " + orderDate +
            ", materialCode = " + materialCode +
            ", materialName = " + materialName +
            ", modelType = " + modelType +
            ", workshop = " + workshop +
            ", produceCount = " + produceCount +
            ", produceUnit = " + produceUnit +
            ", plannedStarttime = " + plannedStarttime +
            ", plannedEndtime = " + plannedEndtime +
            ", addedTime = " + addedTime +
            ", flowCode = " + flowCode +
            ", flowAssembleShell = " + flowAssembleShell +
            ", bomVersion = " + bomVersion +
            ", scheduleState = " + scheduleState +
            ", cmosPn = " + cmosPn +
            ", lensSn = " + lensSn +
            ", fpgaVersion = " + fpgaVersion +
            ", orderState = " + orderState +
            ", completedCount = " + completedCount +
            ", productType = " + productType +
            ", tagSnCanExceedPlannedCount = " + tagSnCanExceedPlannedCount +
            ", paramsDetailId = " + paramsDetailId +
        "}";
    }
}
