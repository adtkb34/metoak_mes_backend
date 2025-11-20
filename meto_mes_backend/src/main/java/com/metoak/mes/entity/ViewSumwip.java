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
@TableName("view_sumwip")
@ApiModel(value = "ViewSumwip对象", description = "VIEW")
public class ViewSumwip implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long num;

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "ViewSumwip{" +
            "num = " + num +
        "}";
    }
}
