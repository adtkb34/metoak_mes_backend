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
 * 
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@TableName("mo_rejection_info")
@ApiModel(value = "MoRejectionInfo对象", description = "")
public class MoRejectionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer workstationId;

    private Integer descriptionId;

    private String produceOrderCode;

    private String productId;

    private Integer total;

    private Integer quantity;

    private LocalDate rejectdate;

    private String userAdd;

    private LocalDateTime timestampAdd;

    private String reworkDescription;

    private Boolean repaired;

    private String userUpd;

    private LocalDateTime timestampUpd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkstationId() {
        return workstationId;
    }

    public void setWorkstationId(Integer workstationId) {
        this.workstationId = workstationId;
    }

    public Integer getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(Integer descriptionId) {
        this.descriptionId = descriptionId;
    }

    public String getProduceOrderCode() {
        return produceOrderCode;
    }

    public void setProduceOrderCode(String produceOrderCode) {
        this.produceOrderCode = produceOrderCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getRejectdate() {
        return rejectdate;
    }

    public void setRejectdate(LocalDate rejectdate) {
        this.rejectdate = rejectdate;
    }

    public String getUserAdd() {
        return userAdd;
    }

    public void setUserAdd(String userAdd) {
        this.userAdd = userAdd;
    }

    public LocalDateTime getTimestampAdd() {
        return timestampAdd;
    }

    public void setTimestampAdd(LocalDateTime timestampAdd) {
        this.timestampAdd = timestampAdd;
    }

    public String getReworkDescription() {
        return reworkDescription;
    }

    public void setReworkDescription(String reworkDescription) {
        this.reworkDescription = reworkDescription;
    }

    public Boolean getRepaired() {
        return repaired;
    }

    public void setRepaired(Boolean repaired) {
        this.repaired = repaired;
    }

    public String getUserUpd() {
        return userUpd;
    }

    public void setUserUpd(String userUpd) {
        this.userUpd = userUpd;
    }

    public LocalDateTime getTimestampUpd() {
        return timestampUpd;
    }

    public void setTimestampUpd(LocalDateTime timestampUpd) {
        this.timestampUpd = timestampUpd;
    }

    @Override
    public String toString() {
        return "MoRejectionInfo{" +
            "id = " + id +
            ", workstationId = " + workstationId +
            ", descriptionId = " + descriptionId +
            ", produceOrderCode = " + produceOrderCode +
            ", productId = " + productId +
            ", total = " + total +
            ", quantity = " + quantity +
            ", rejectdate = " + rejectdate +
            ", userAdd = " + userAdd +
            ", timestampAdd = " + timestampAdd +
            ", reworkDescription = " + reworkDescription +
            ", repaired = " + repaired +
            ", userUpd = " + userUpd +
            ", timestampUpd = " + timestampUpd +
        "}";
    }
}
