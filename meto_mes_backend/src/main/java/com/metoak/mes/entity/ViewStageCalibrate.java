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
@TableName("view_stage_calibrate")
@ApiModel(value = "ViewStageCalibrate对象", description = "VIEW")
public class ViewStageCalibrate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long num;

    private String htype;

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getHtype() {
        return htype;
    }

    public void setHtype(String htype) {
        this.htype = htype;
    }

    @Override
    public String toString() {
        return "ViewStageCalibrate{" +
            "num = " + num +
            ", htype = " + htype +
        "}";
    }
}
