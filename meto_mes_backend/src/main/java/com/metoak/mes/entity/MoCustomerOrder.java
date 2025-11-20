package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 客户订单信息表(From ERP)
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@TableName("mo_customer_order")
@ApiModel(value = "MoCustomerOrder对象", description = "客户订单信息表(From ERP)")
public class MoCustomerOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("单据编号")
    private String orderCode;

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

    @ApiModelProperty("产品数量")
    private Integer productCount;

    @ApiModelProperty("单位")
    private String productUnit;

    @ApiModelProperty("业务状态")
    private String businessState;

    @ApiModelProperty("领料状态")
    private String materialState;

    private LocalDateTime planBegin;

    private LocalDateTime planEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getBusinessState() {
        return businessState;
    }

    public void setBusinessState(String businessState) {
        this.businessState = businessState;
    }

    public String getMaterialState() {
        return materialState;
    }

    public void setMaterialState(String materialState) {
        this.materialState = materialState;
    }

    public LocalDateTime getPlanBegin() {
        return planBegin;
    }

    public void setPlanBegin(LocalDateTime planBegin) {
        this.planBegin = planBegin;
    }

    public LocalDateTime getPlanEnd() {
        return planEnd;
    }

    public void setPlanEnd(LocalDateTime planEnd) {
        this.planEnd = planEnd;
    }

    @Override
    public String toString() {
        return "MoCustomerOrder{" +
            "id = " + id +
            ", orderCode = " + orderCode +
            ", orderDate = " + orderDate +
            ", materialCode = " + materialCode +
            ", materialName = " + materialName +
            ", modelType = " + modelType +
            ", workshop = " + workshop +
            ", productCount = " + productCount +
            ", productUnit = " + productUnit +
            ", businessState = " + businessState +
            ", materialState = " + materialState +
            ", planBegin = " + planBegin +
            ", planEnd = " + planEnd +
        "}";
    }
}
