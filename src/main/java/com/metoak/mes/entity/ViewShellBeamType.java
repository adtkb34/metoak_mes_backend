package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@TableName("view_shell_beam_type")
@ApiModel(value = "ViewShellBeamType对象", description = "VIEW")
public class ViewShellBeamType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("外壳SN")
    private String shellSn;

    private String beamSn;

    private Integer orderId;

    @ApiModelProperty("工单编号")
    private String orderCode;

    private String productType;

    private Long rownum;

    public String getShellSn() {
        return shellSn;
    }

    public void setShellSn(String shellSn) {
        this.shellSn = shellSn;
    }

    public String getBeamSn() {
        return beamSn;
    }

    public void setBeamSn(String beamSn) {
        this.beamSn = beamSn;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Long getRownum() {
        return rownum;
    }

    public void setRownum(Long rownum) {
        this.rownum = rownum;
    }

    @Override
    public String toString() {
        return "ViewShellBeamType{" +
            "shellSn = " + shellSn +
            ", beamSn = " + beamSn +
            ", orderId = " + orderId +
            ", orderCode = " + orderCode +
            ", productType = " + productType +
            ", rownum = " + rownum +
        "}";
    }
}
