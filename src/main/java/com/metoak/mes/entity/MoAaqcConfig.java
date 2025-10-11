package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("mo_aaqc_config")
@ApiModel(value = "MoAaqcConfig对象", description = "")
public class MoAaqcConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer typeNum1;

    private Integer typeNum2;

    private BigDecimal thresholdDiff;

    private Integer thresholdCount;

    private LocalDateTime operationTime;

    private String operator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeNum1() {
        return typeNum1;
    }

    public void setTypeNum1(Integer typeNum1) {
        this.typeNum1 = typeNum1;
    }

    public Integer getTypeNum2() {
        return typeNum2;
    }

    public void setTypeNum2(Integer typeNum2) {
        this.typeNum2 = typeNum2;
    }

    public BigDecimal getThresholdDiff() {
        return thresholdDiff;
    }

    public void setThresholdDiff(BigDecimal thresholdDiff) {
        this.thresholdDiff = thresholdDiff;
    }

    public Integer getThresholdCount() {
        return thresholdCount;
    }

    public void setThresholdCount(Integer thresholdCount) {
        this.thresholdCount = thresholdCount;
    }

    public LocalDateTime getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(LocalDateTime operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "MoAaqcConfig{" +
            "id = " + id +
            ", typeNum1 = " + typeNum1 +
            ", typeNum2 = " + typeNum2 +
            ", thresholdDiff = " + thresholdDiff +
            ", thresholdCount = " + thresholdCount +
            ", operationTime = " + operationTime +
            ", operator = " + operator +
        "}";
    }
}
