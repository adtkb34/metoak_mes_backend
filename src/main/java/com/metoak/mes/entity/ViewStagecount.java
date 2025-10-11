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
@TableName("view_stagecount")
@ApiModel(value = "ViewStagecount对象", description = "VIEW")
public class ViewStagecount implements Serializable {

    private static final long serialVersionUID = 1L;

    private String days;

    private Long num;

    private String stage;

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return "ViewStagecount{" +
            "days = " + days +
            ", num = " + num +
            ", stage = " + stage +
        "}";
    }
}
