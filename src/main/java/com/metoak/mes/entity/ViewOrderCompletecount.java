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
@TableName("view_order_completecount")
@ApiModel(value = "ViewOrderCompletecount对象", description = "VIEW")
public class ViewOrderCompletecount implements Serializable {

    private static final long serialVersionUID = 1L;

    private String workOrderCode;

    private Long completeNum;

    public String getWorkOrderCode() {
        return workOrderCode;
    }

    public void setWorkOrderCode(String workOrderCode) {
        this.workOrderCode = workOrderCode;
    }

    public Long getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Long completeNum) {
        this.completeNum = completeNum;
    }

    @Override
    public String toString() {
        return "ViewOrderCompletecount{" +
            "workOrderCode = " + workOrderCode +
            ", completeNum = " + completeNum +
        "}";
    }
}
